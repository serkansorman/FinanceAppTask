package com.example.ccfinancegrouptask.ui.stocklist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ccfinancegrouptask.common.Resource
import com.example.ccfinancegrouptask.domain.usecase.GetStockDescriptionUseCase
import com.example.ccfinancegrouptask.ui.stockdescription.event.StockDescriptionEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StockDescriptionViewModel(private val getStockDescriptionUseCase: GetStockDescriptionUseCase
): ViewModel() {

    private val _stockDescriptionFlow = MutableStateFlow<StockDescriptionEvent>(
        StockDescriptionEvent.Idle)
    val stockDescriptionFlow = _stockDescriptionFlow.asStateFlow()

    fun getStockDescription(symbol: String?) {
        viewModelScope.launch {
            _stockDescriptionFlow.value = StockDescriptionEvent.Loading
            when (val result = getStockDescriptionUseCase(GetStockDescriptionUseCase.Param(symbol))) {
                is Resource.Success -> {
                    _stockDescriptionFlow.value = StockDescriptionEvent.Success(result.data)
                }
                is Resource.Failure -> {
                    _stockDescriptionFlow.value = StockDescriptionEvent.Failure(result.errorPrompt)
                }
            }
        }
    }
}