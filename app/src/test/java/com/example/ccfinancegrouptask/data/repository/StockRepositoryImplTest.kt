package com.example.ccfinancegrouptask.data.repository

import com.example.ccfinancegrouptask.common.Resource
import com.example.ccfinancegrouptask.data.remote.datasource.StockRemoteDataSource
import com.example.ccfinancegrouptask.domain.repository.StockRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Response
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class LiveSupportRepositoryImplTest {

    private val remoteDataSource = mockk<StockRemoteDataSource>()
    private val response = mockk<Response>(relaxed = true)
    private lateinit var stockRepository: StockRepository

    @Before
    fun setUp() {
        stockRepository =
            StockRepositoryImpl(remoteDataSource)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `When the getStockList called on repository, verify that getStockList called in data source`() {
        runBlocking {
            // given
            coEvery { remoteDataSource.getStockList() } returns response

            // when
            stockRepository.getStockListFlow().first()

            // then
            coVerify { remoteDataSource.getStockList() }
        }
    }

    @Test
    fun `When the result of getStockList return error response, return Resource Failure`() {
        runBlocking {
            // given
            coEvery { remoteDataSource.getStockList() } returns response

            // when
            val resource = stockRepository.getStockListFlow().first()

            // then
            coVerify { remoteDataSource.getStockList() }
            Assert.assertTrue(resource is Resource.Failure)
        }
    }

    @Test
    fun `When the getStockBySymbol called on repository, verify that getStockDescription called in data source`() {
        runBlocking {
            // given
            coEvery { remoteDataSource.getStockDescription(any()) } returns response

            // when
            stockRepository.getStockBySymbol("dummySymbol")

            // then
            coVerify { remoteDataSource.getStockDescription(any()) }
        }
    }

    @Test
    fun `When the result of getStockBySymbol return error response, return Resource Failure`() {
        runBlocking {
            // given
            coEvery { remoteDataSource.getStockDescription(any()) } returns response

            // when
            val resource = stockRepository.getStockBySymbol("dummySymbol")

            // then
            coVerify { remoteDataSource.getStockDescription(any()) }
            Assert.assertTrue(resource is Resource.Failure)
        }
    }
}