package com.example.ccfinancegrouptask.domain.usecase

import com.example.ccfinancegrouptask.common.Resource
import com.example.ccfinancegrouptask.data.model.response.StockDescriptionResponseModel
import com.example.ccfinancegrouptask.domain.mapper.StockDescriptionUIMapper
import com.example.ccfinancegrouptask.domain.model.ErrorPrompt
import com.example.ccfinancegrouptask.domain.repository.StockRepository
import com.example.ccfinancegrouptask.util.TestData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetStockDescriptionUseCaseTest {

    private var stockRepository = mockk<StockRepository>()
    private lateinit var getStockDescriptionUseCase: GetStockDescriptionUseCase

    @Before
    fun setUp() {
        getStockDescriptionUseCase = GetStockDescriptionUseCase(stockRepository, StockDescriptionUIMapper())
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `When the result of getStockBySymbol is successful, return Resource Success with mapped UI model`() {
        runBlocking {
            // given
            coEvery { stockRepository.getStockBySymbol(any()) } returns Resource.Success(
               TestData.stockDescriptionResponseModel
            )

            // when
            val resource = getStockDescriptionUseCase(GetStockDescriptionUseCase.Param("dummySymbol"))

            // then
            coVerify { stockRepository.getStockBySymbol(any()) }
            assertEquals(TestData.stockDescriptionUIModel, (resource as Resource.Success).data)
        }
    }

    @Test
    fun `When the result of getStockBySymbol is failure, return Resource Failure`() {
        runBlocking {
            // given
            coEvery { stockRepository.getStockBySymbol(any()) } returns Resource.Failure(
                TestData.errorPrompt
            )

            // when
            val resource = getStockDescriptionUseCase(GetStockDescriptionUseCase.Param("dummySymbol"))

            // then
            coVerify { stockRepository.getStockBySymbol(any()) }
            assertTrue(resource is Resource.Failure)
        }
    }

    @Test
    fun `When the result of getStockBySymbol throws Exception, return Resource Failure`() {
        runBlocking {
            // given
            coEvery { stockRepository.getStockBySymbol(any()) }.throws(Exception())

            // when
            val resource = getStockDescriptionUseCase(GetStockDescriptionUseCase.Param(TestData.stockSymbol))

            // then
            coVerify { stockRepository.getStockBySymbol(any()) }
            assertTrue(resource is Resource.Failure)
        }
    }
}