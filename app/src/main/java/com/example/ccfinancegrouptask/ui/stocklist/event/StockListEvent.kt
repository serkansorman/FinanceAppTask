package com.example.ccfinancegrouptask.ui.stocklist.event

import com.example.ccfinancegrouptask.data.model.StockModel
import com.example.ccfinancegrouptask.domain.model.ErrorPrompt

sealed class StockListEvent {
    class Success(val stockList: List<StockModel>) : StockListEvent() //TODO UI Model
    class Failure(val errorPrompt : ErrorPrompt) : StockListEvent()
    object Loading : StockListEvent()
    object Idle : StockListEvent()
}