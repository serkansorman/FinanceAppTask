package com.example.ccfinancegrouptask.data.model.response

import com.google.gson.annotations.SerializedName

data class StockDescriptionResponseModel(
    @SerializedName("price")
    val data: Data?
)

data class Data(
    val exchange: String?,
    val shortName: String?,
    val longName: String?,
    val exchangeName: String?,
    val currency: String?
)


