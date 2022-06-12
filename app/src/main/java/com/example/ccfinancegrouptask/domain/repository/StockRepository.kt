package com.example.ccfinancegrouptask.domain.repository

import com.example.ccfinancegrouptask.data.model.response.StockListResponseModel

interface StockRepository {

    suspend fun getStockList() : StockListResponseModel

    fun getStockBySymbol()
}