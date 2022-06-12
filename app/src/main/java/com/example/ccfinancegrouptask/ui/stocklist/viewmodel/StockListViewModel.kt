package com.example.ccfinancegrouptask.ui.stocklist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ccfinancegrouptask.common.Resource
import com.example.ccfinancegrouptask.domain.repository.StockRepository
import com.example.ccfinancegrouptask.domain.usecase.GetStockListUseCase
import com.example.ccfinancegrouptask.ui.stocklist.event.StockListEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockListViewModel @Inject constructor(private val getStockListUseCase: GetStockListUseCase) : ViewModel() {

    private val _stockListFlow = MutableStateFlow<StockListEvent>(StockListEvent.Idle)
    val stockListStateFlow = _stockListFlow.asStateFlow()


    fun getStockList(){
        viewModelScope.launch {
            when(val result = getStockListUseCase(Unit)){
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