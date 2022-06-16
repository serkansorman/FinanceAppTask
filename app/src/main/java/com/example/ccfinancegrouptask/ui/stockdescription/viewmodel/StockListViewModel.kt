package com.example.ccfinancegrouptask.ui.stockdescription.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ccfinancegrouptask.common.Resource
import com.example.ccfinancegrouptask.domain.usecase.GetStockListUseCase
import com.example.ccfinancegrouptask.ui.stocklist.event.StockListEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StockListViewModel(
    private val getStockListUseCase: GetStockListUseCase,
) : ViewModel() {

    private val _stockListFlow = MutableStateFlow<StockListEvent>(StockListEvent.Idle)
    val stockListStateFlow = _stockListFlow.asStateFlow()

    fun getStockList() {
        viewModelScope.launch {
            when (val result = getStockListUseCase(Unit)) {
                is Resource.Success -> {
                    _stockListFlow.value = StockListEvent.Success(result.data)
                }
                is Resource.Failure -> {
                    _stockListFlow.value = StockListEvent.Failure(result.errorPrompt)
                }
            }
        }
    }
}