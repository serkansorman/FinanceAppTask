package com.example.ccfinancegrouptask.util

import com.example.ccfinancegrouptask.data.model.response.*
import com.example.ccfinancegrouptask.domain.model.ErrorPrompt
import com.example.ccfinancegrouptask.domain.model.StockDescriptionUIModel

object TestData {

    val stockModel = StockModel("BTC-USD", null, null, null, null)

    val stockListResponseModel = StockListResponseModel(ResultData(listOf(stockModel), null))

    val stockDescriptionResponseModel =
        StockDescriptionResponseModel(Summary("BTC-USD", null, null, null, null, null, null, null))

    val stockDescriptionUIModel =
        StockDescriptionUIModel("BTC-USD", null, null, null, null, null, null, null)

    val errorPrompt = ErrorPrompt(500, "dummyErrorMessage")

    const val stockSymbol = "BTC-USD"
}