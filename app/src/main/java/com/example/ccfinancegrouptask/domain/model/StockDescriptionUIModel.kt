package com.example.ccfinancegrouptask.domain.model

data class StockDescriptionUIModel(
    val shortName: String?,
    val currency: String?,
    val lastPrice: String?,
    val changePercent: String?,
    val openPrice: String?,
    val closePrice: String?,
    val priceLow: String?,
    val priceHigh: String?
)