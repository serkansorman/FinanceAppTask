package com.example.ccfinancegrouptask.data.remote.datasource

import com.example.ccfinancegrouptask.common.Constants
import com.example.ccfinancegrouptask.common.extension.sendRequest
import okhttp3.OkHttpClient
import okhttp3.Response

class StockRemoteDataSourceImpl(private val client: OkHttpClient) : StockRemoteDataSource {
    override suspend fun getStockList(): Response {
        return client.sendRequest(endPoint = Constants.STOCK_LIST_ENDPOINT)
    }

    override suspend fun getStockDescription(symbol: String): Response {
        return client.sendRequest(
            endPoint = Constants.STOCK_DESCRIPTION_ENDPOINT,
            mapOf(Constants.QUERY_SYMBOL to symbol)
        )
    }
}