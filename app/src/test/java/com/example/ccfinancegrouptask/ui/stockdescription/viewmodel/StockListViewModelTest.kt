package com.example.ccfinancegrouptask.ui.stockdescription.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.ccfinancegrouptask.common.Resource
import com.example.ccfinancegrouptask.domain.usecase.GetStockListFlowUseCase
import com.example.ccfinancegrouptask.domain.usecase.GetStockListSingleUseCase
import com.example.ccfinancegrouptask.ui.stocklist.event.StockListEvent
import com.example.ccfinancegrouptask.util.TestCoroutineRule
import com.example.ccfinancegrouptask.util.TestData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class StockListViewModelTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: StockListViewModel

    @MockK
    private lateinit var getStockListFlowUseCase: GetStockListFlowUseCase

    @MockK
    private lateinit var getStockListSingleUseCase: GetStockListSingleUseCase

    @Before
    fun before() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when getStockListFlowUseCase emits success, return Success Event with expected stock list`() {
        testCoroutineRule.runBlockingTest {
            //given
            val expectedList = mutableListOf(TestData.stockModel)
            coEvery { getStockListFlowUseCase(Unit) } returns flowOf(Resource.Success(expectedList))

            //when
            viewModel =
                spyk(StockListViewModel(getStockListFlowUseCase, getStockListSingleUseCase))

            //then
            val actual = viewModel.stockListFlow.first()
            assertEquals(expectedList.first(), (actual as StockListEvent.Success).stockList.first())

        }
    }

    @Test
    fun `when getStockListFlowUseCase emits failure, return Failure Event with errorPrompt`() {
        testCoroutineRule.runBlockingTest {
            //given
            val expectedErrorPrompt = TestData.errorPrompt
            coEvery { getStockListFlowUseCase(Unit) } returns flowOf(
                Resource.Failure(
                    expectedErrorPrompt
                )
            )

            //when
            viewModel =
                spyk(StockListViewModel(getStockListFlowUseCase, getStockListSingleUseCase))

            //then
            val actual = viewModel.stockListFlow.first()
            assertEquals(expectedErrorPrompt, (actual as StockListEvent.Failure).errorPrompt)

        }
    }

    @Test
    fun `when getStockListSingleUseCase result is success, return Success Event with expected stock list`() {
        testCoroutineRule.runBlockingTest {
            //given
            val expectedList = mutableListOf(TestData.stockModel)
            coEvery { getStockListSingleUseCase(Unit) } returns Resource.Success(expectedList)
            coEvery { getStockListFlowUseCase(Unit) } returns flow {}
            viewModel = spyk(StockListViewModel(getStockListFlowUseCase, getStockListSingleUseCase))

            //when
            viewModel.refreshStockList()

            //then
            val actual = viewModel.stockListFlow.first()
            assertEquals(expectedList.first(), (actual as StockListEvent.Success).stockList.first())

        }
    }

    @Test
    fun `when getStockListSingleUseCase result is failure, return Failure Event with errorPrompt`() {
        testCoroutineRule.runBlockingTest {
            //given
            val expectedErrorPrompt = TestData.errorPrompt
            coEvery { getStockListSingleUseCase(Unit) } returns Resource.Failure(expectedErrorPrompt)
            coEvery { getStockListFlowUseCase(Unit) } returns flow {}
            viewModel = spyk(StockListViewModel(getStockListFlowUseCase, getStockListSingleUseCase))

            //when
            viewModel.refreshStockList()

            //then
            val actual = viewModel.stockListFlow.first()
            assertEquals(expectedErrorPrompt, (actual as StockListEvent.Failure).errorPrompt)

        }
    }


}