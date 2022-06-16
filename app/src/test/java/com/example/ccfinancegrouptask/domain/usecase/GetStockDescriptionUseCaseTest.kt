package com.example.ccfinancegrouptask.domain.usecase

import com.example.ccfinancegrouptask.common.Resource
import com.example.ccfinancegrouptask.data.model.response.StockDescriptionResponseModel
import com.example.ccfinancegrouptask.domain.mapper.StockDescriptionUIMapper
import com.example.ccfinancegrouptask.domain.model.ErrorPrompt
import com.example.ccfinancegrouptask.domain.repository.StockRepository
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
    fun `When the result of getStockBySymbol is successful, return Resource Success`() {
        runBlocking {
            // given
            coEvery { stockRepository.getStockBySymbol(any()) } returns Resource.Success(
               StockDescriptionResponseModel(mockk(relaxed = true))
            )

            // when
            val resource = getStockDescriptionUseCase(GetStockDescriptionUseCase.Param("dummySymbol"))

            // then
            coVerify { stockRepository.getStockBySymbol(any()) }
            assertTrue(resource is Resource.Success)
        }
    }

    @Test
    fun `When the result of getStockBySymbol is failure, return Resource Failure`() {
        runBlocking {
            // given
            coEvery { stockRepository.getStockBySymbol(any()) } returns Resource.Failure(
                ErrorPrompt(-1, "dummyMessage")
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
            val resource = getStockDescriptionUseCase(GetStockDescriptionUseCase.Param("dummySymbol"))

            // then
            coVerify { stockRepository.getStockBySymbol(any()) }
            assertTrue(resource is Resource.Failure)
        }
    }
}