package com.example.ccfinancegrouptask.data.remote.datasource

import okhttp3.Response


interface StockRemoteDataSource {
    suspend fun getStockList(): Response
    suspend fun getStockDescription(symbol: String): Response
}