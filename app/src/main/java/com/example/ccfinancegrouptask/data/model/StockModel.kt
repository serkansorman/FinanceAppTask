package com.example.ccfinancegrouptask.data.model

import com.google.gson.annotations.SerializedName

data class StockModel(
    val symbol: String?,
    val exchange: String?,
    val fullExchangeName: String?,
    val shortName: String?,
    @SerializedName("regularMarketPreviousClose")
    val marketClosePrice: ClosePrice?
)

data class ClosePrice(val raw: String?, val fmt: String?)