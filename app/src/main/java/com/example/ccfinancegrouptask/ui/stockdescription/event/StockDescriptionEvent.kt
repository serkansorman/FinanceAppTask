package com.example.ccfinancegrouptask.ui.stockdescription.event

import com.example.ccfinancegrouptask.domain.model.ErrorPrompt
import com.example.ccfinancegrouptask.domain.model.StockDescriptionUIModel

sealed class StockDescriptionEvent {
    class Success(val stockDescription: StockDescriptionUIModel) : StockDescriptionEvent()
    class Failure(val errorPrompt : ErrorPrompt) : StockDescriptionEvent()
    object Loading : StockDescriptionEvent()
    object Idle : StockDescriptionEvent()
}