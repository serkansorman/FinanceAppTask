package com.example.ccfinancegrouptask.domain.repository

import com.example.ccfinancegrouptask.common.Resource
import com.example.ccfinancegrouptask.data.model.response.StockListResponseModel

interface StockRepository {

    suspend fun getStockList() : Resource<StockListResponseModel>

    fun getStockBySymbol()
}