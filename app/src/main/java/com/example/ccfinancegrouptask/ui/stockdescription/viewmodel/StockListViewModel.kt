package com.example.ccfinancegrouptask.ui.stockdescription.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ccfinancegrouptask.common.Resource
import com.example.ccfinancegrouptask.data.model.response.StockModel
import com.example.ccfinancegrouptask.domain.usecase.GetStockListFlowUseCase
import com.example.ccfinancegrouptask.domain.usecase.GetStockListSingleUseCase
import com.example.ccfinancegrouptask.ui.stocklist.event.StockListEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class StockListViewModel(
    private val getStockListFlowUseCase: GetStockListFlowUseCase,
    private val getStockListSingleUseCase: GetStockListSingleUseCase
) : ViewModel() {

    private val _refreshStockListFlow = MutableStateFlow<StockListEvent>(StockListEvent.Idle)

    val stockListFlow: StateFlow<StockListEvent> =
        merge(_refreshStockListFlow, getStockListFlowUseCase(Unit)
            .map { result ->
                handleStockListResult(result)
            })
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(),
                StockListEvent.Idle
            )


    fun refreshStockList() {
        viewModelScope.launch {
            _refreshStockListFlow.value = StockListEvent.Loading
            getStockListSingleUseCase(Unit).let { result ->
                _refreshStockListFlow.value = handleStockListResult(result)
            }
        }
    }

    private fun handleStockListResult(result: Resource<List<StockModel>>) =
        when (result) {
            is Resource.Success -> {
                StockListEvent.Success(result.data)
            }
            is Resource.Failure -> {
                StockListEvent.Failure(result.errorPrompt)
            }
        }

}