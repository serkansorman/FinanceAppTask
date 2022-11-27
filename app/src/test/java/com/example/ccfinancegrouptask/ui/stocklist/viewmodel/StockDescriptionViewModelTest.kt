package com.example.ccfinancegrouptask.ui.stocklist.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.ccfinancegrouptask.common.Resource
import com.example.ccfinancegrouptask.domain.usecase.GetStockDescriptionUseCase
import com.example.ccfinancegrouptask.ui.stockdescription.event.StockDescriptionEvent
import com.example.ccfinancegrouptask.util.TestCoroutineRule
import com.example.ccfinancegrouptask.util.TestData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class StockDescriptionViewModelTest{

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: StockDescriptionViewModel

    @MockK
    private lateinit var getStockDescriptionUseCase: GetStockDescriptionUseCase

    @Before
    fun before() {
        MockKAnnotations.init(this)
        viewModel =
            spyk(StockDescriptionViewModel(getStockDescriptionUseCase))
    }

    @Test
    fun `when getStockDescriptionUseCase result is success, return Success Event with expected stock description`() {
        testCoroutineRule.runBlockingTest {
            //given
            val expected = TestData.stockDescriptionUIModel
            coEvery { getStockDescriptionUseCase(GetStockDescriptionUseCase.Param(TestData.stockSymbol))} returns Resource.Success(
                expected
            )

            //when
            viewModel.getStockDescription(TestData.stockSymbol)

            //then
            val actual = viewModel.stockDescriptionFlow.first()
            assertEquals(expected, (actual as StockDescriptionEvent.Success).stockDescription)

        }
    }

    @Test
    fun `when getStockDescriptionUseCase result is failure, return Failure Event with errorPrompt`() {
        testCoroutineRule.runBlockingTest {
            //given
            val expectedErrorPrompt = TestData.errorPrompt
            coEvery { getStockDescriptionUseCase(GetStockDescriptionUseCase.Param(TestData.stockSymbol))} returns Resource.Failure(
                expectedErrorPrompt
            )

            //when
            viewModel.getStockDescription("BTC-USD")

            //then
            val actual = viewModel.stockDescriptionFlow.first()
            assertEquals(expectedErrorPrompt, (actual as StockDescriptionEvent.Failure).errorPrompt)

        }
    }
}