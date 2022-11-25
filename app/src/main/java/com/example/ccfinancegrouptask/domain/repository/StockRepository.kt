package com.example.ccfinancegrouptask.domain.repository

import com.example.ccfinancegrouptask.common.Resource
import com.example.ccfinancegrouptask.data.model.response.StockDescriptionResponseModel
import com.example.ccfinancegrouptask.data.model.response.StockListResponseModel
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    fun getStockListFlow(): Flow<Resource<StockListResponseModel>>

    suspend fun getStockBySymbol(symbol: String) : Resource<StockDescriptionResponseModel>
}