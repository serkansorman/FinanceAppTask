package com.example.ccfinancegrouptask.ui.stocklist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ccfinancegrouptask.data.model.response.Data
import com.example.ccfinancegrouptask.data.model.response.StockListResponseModel
import com.example.ccfinancegrouptask.domain.repository.StockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockListViewModel @Inject constructor(private val stockRepository: StockRepository) : ViewModel() {

    private val _stockListFlow = MutableStateFlow<StockListResponseModel>(
        StockListResponseModel(
            Data(emptyList(),null)
    ))

    val stockListStateFlow = _stockListFlow.asStateFlow()


    fun getStockList(){
        viewModelScope.launch {
            _stockListFlow.value = stockRepository.getStockList()
        }
    }
}