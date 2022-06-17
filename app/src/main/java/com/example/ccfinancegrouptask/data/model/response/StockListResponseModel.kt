package com.example.ccfinancegrouptask.data.model.response

import com.google.gson.annotations.SerializedName

data class StockListResponseModel(
    @SerializedName("marketSummaryAndSparkResponse")
    val resultData: ResultData
)

data class ResultData(val result: List<StockModel>,
                      val error: String?)