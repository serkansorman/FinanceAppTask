package com.example.ccfinancegrouptask.data.remote.datasource

import com.example.ccfinancegrouptask.common.Constants
import com.example.ccfinancegrouptask.data.model.response.StockListResponseModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface StockRemoteDataSource {

    @Headers(Constants.API_JSON_UTF_CONTENT)
    @GET(Constants.STOCK_LIST_ENDPOINT)
    suspend fun getStockList(@Query("region") region: String = Constants.REGION_US): StockListResponseModel

}