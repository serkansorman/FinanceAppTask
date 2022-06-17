package com.example.ccfinancegrouptask.ui.stockdescription.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.ccfinancegrouptask.common.Resource
import com.example.ccfinancegrouptask.data.model.response.StockModel
import com.example.ccfinancegrouptask.domain.model.ErrorPrompt
import com.example.ccfinancegrouptask.domain.repository.StockRepository
import com.example.ccfinancegrouptask.domain.usecase.GetStockListUseCase
import com.example.ccfinancegrouptask.ui.stocklist.event.StockListEvent
import com.example.ccfinancegrouptask.util.TestCoroutineRule
import com.example.ccfinancegrouptask.util.TestData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import org.junit.Assert.*
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
    private lateinit var getStockListUseCase: GetStockListUseCase

    @Before
    fun before() {
        MockKAnnotations.init(this)
        viewModel =
            spyk(StockListViewModel(getStockListUseCase))
    }

    @Test
    fun `when getStockListUseCase result is success, return Success Event with expected stock list`() {
        testCoroutineRule.runBlockingTest {
            //given
            val expectedList = mutableListOf(TestData.stockModel)
            coEvery { getStockListUseCase(Unit)} returns Resource.Success(expectedList)

            //when
            viewModel.getStockList()

            //then
           val actual = viewModel.stockListStateFlow.first()
            assertEquals(expectedList.first(), (actual as StockListEvent.Success).stockList.first())

        }
    }

    @Test
    fun `when getStockListUseCase result is failure, return Failure Event with errorPrompt`() {
        testCoroutineRule.runBlockingTest {
            //given
            val expectedErrorPrompt = TestData.errorPrompt
            coEvery { getStockListUseCase(Unit)} returns Resource.Failure(expectedErrorPrompt)

            //when
            viewModel.getStockList()

            //then
            val actual = viewModel.stockListStateFlow.first()
            assertEquals(expectedErrorPrompt, (actual as StockListEvent.Failure).errorPrompt)

        }
    }
}