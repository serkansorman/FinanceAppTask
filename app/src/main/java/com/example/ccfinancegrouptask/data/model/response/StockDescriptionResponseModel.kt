package com.example.ccfinancegrouptask.data.model.response

import com.google.gson.annotations.SerializedName


data class StockDescriptionResponseModel(
    @SerializedName("price")
    val summary: Summary?
)

data class Summary(
    val shortName: String?,
    val currency: String?,
    val regularMarketPrice: RegularMarketPrice?,
    val regularMarketChangePercent: RegularMarketPrice?,
    val regularMarketOpen: RegularMarketPrice?,
    val regularMarketPreviousClose: RegularMarketPrice?,
    val regularMarketDayLow: RegularMarketPrice?,
    val regularMarketDayHigh: RegularMarketPrice?
)

data class RegularMarketPrice(val raw: String?, val fmt: String?)
