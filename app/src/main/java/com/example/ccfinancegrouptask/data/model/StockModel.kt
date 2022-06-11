package com.example.ccfinancegrouptask.data.model

data class StockModel(
    val symbol: String?,
    val exchange: String?,
    val fullExchangeName: String?,
    val quoteType: String?,
    val previousClose: String?,
    val chartPreviousClose: String?,
)