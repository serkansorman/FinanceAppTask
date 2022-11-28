package com.example.ccfinancegrouptask.domain.usecase

import com.example.ccfinancegrouptask.common.AppDispatchers
import com.example.ccfinancegrouptask.common.Resource
import com.example.ccfinancegrouptask.domain.repository.StockRepository
import com.example.ccfinancegrouptask.util.TestData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetStockListSingleUseCaseTest {

    private var stockRepository = mockk<StockRepository>()
    private lateinit var getStockListSingleUseCase: GetStockListSingleUseCase
    private val appDispatchers =
        AppDispatchers(Dispatchers.Main, Dispatchers.IO, Dispatchers.Default)

    @Before
    fun setUp() {
        getStockListSingleUseCase = GetStockListSingleUseCase(stockRepository, appDispatchers)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `When the result of getSingleStockList is successful, return Resource Success with expected stock list`() {
        runBlocking {
            // given
            coEvery { stockRepository.getSingleStockList() } returns Resource.Success(TestData.stockListResponseModel)

            // when
            val resource = getStockListSingleUseCase(Unit)

            // then
            coVerify { stockRepository.getSingleStockList() }
            Assert.assertEquals(TestData.stockModel, (resource as Resource.Success).data.first())
        }
    }

    @Test
    fun `When the result of getSingleStockList is failure, return Resource Failure`() {
        runBlocking {
            // given
            coEvery { stockRepository.getSingleStockList() } returns Resource.Failure(TestData.errorPrompt)

            // when
            val resource = getStockListSingleUseCase(Unit)

            // then
            coVerify { stockRepository.getSingleStockList() }
            Assert.assertTrue(resource is Resource.Failure)
        }
    }

    @Test
    fun `When the result of getSingleStockList throws Exception, return Resource Failure`() {
        runBlocking {
            // given
            coEvery { stockRepository.getSingleStockList() }.throws(Exception())

            // when
            val resource = getStockListSingleUseCase(Unit)

            // then
            coVerify { stockRepository.getSingleStockList() }
            Assert.assertTrue(resource is Resource.Failure)
        }
    }
}