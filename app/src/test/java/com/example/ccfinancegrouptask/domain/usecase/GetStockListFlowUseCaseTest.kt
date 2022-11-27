package com.example.ccfinancegrouptask.domain.usecase

import com.example.ccfinancegrouptask.common.Resource
import com.example.ccfinancegrouptask.domain.repository.StockRepository
import com.example.ccfinancegrouptask.util.TestData
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetStockListFlowUseCaseTest {

    private var stockRepository = mockk<StockRepository>()
    private lateinit var getStockListFlowUseCase: GetStockListFlowUseCase

    @Before
    fun setUp() {
        getStockListFlowUseCase = GetStockListFlowUseCase(stockRepository)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `When getStockListFlow emit success, return Resource Success with expected stock list`() {
        runBlocking {
            // given
            coEvery { stockRepository.getStockListFlow() } returns flowOf(
                Resource.Success(
                    TestData.stockListResponseModel
                )
            )

            // when
            val resource = getStockListFlowUseCase(Unit).first()

            // then
            coVerify { stockRepository.getStockListFlow() }
            Assert.assertEquals(TestData.stockModel, (resource as Resource.Success).data.first())
        }
    }

    @Test
    fun `When getStockListFlow emits failure, return Resource Failure`() {
        runBlocking {
            // given
            coEvery { stockRepository.getStockListFlow() } returns flowOf(
                Resource.Failure(
                    TestData.errorPrompt
                )
            )

            // when
            val resource = getStockListFlowUseCase(Unit).first()

            // then
            coVerify { stockRepository.getStockListFlow() }
            Assert.assertTrue(resource is Resource.Failure)
        }
    }

    @Test
    fun `When getStockListFlow throws Exception, return Resource Failure`() {
        runBlocking {
            // given
            coEvery { stockRepository.getStockListFlow() }.throws(Exception())

            // when
            val resource = getStockListFlowUseCase(Unit).first()

            // then
            coVerify { stockRepository.getStockListFlow() }
            Assert.assertTrue(resource is Resource.Failure)
        }
    }
}