package com.example.ccfinancegrouptask.domain.repository

import com.example.ccfinancegrouptask.common.Resource
import com.example.ccfinancegrouptask.data.model.response.StockListResponseModel
import okhttp3.ResponseBody

interface StockRepository {

    suspend fun getStockList() : Resource<StockListResponseModel>

    fun getStockBySymbol()
}