package com.example.ccfinancegrouptask.data.model.response

import com.example.ccfinancegrouptask.data.model.StockModel
import com.google.gson.annotations.SerializedName

data class StockListResponseModel(
    @SerializedName("marketSummaryAndSparkResponse")
    val data: Data
)

data class Data(val result: List<StockModel>,
                val error: String?)