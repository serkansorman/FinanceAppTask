package com.example.ccfinancegrouptask.ui.stockdescription.event

import com.example.ccfinancegrouptask.data.model.response.StockDescriptionResponseModel
import com.example.ccfinancegrouptask.domain.model.ErrorPrompt

sealed class StockDescriptionEvent {
    class Success(val stockDescription: StockDescriptionResponseModel) : StockDescriptionEvent() //TODO UI Model
    class Failure(val errorPrompt : ErrorPrompt) : StockDescriptionEvent()
    object Loading : StockDescriptionEvent()
    object Idle : StockDescriptionEvent()
}