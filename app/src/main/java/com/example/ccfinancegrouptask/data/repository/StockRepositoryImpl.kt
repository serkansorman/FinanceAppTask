package com.example.ccfinancegrouptask.data.repository

import com.example.ccfinancegrouptask.common.Resource
import com.example.ccfinancegrouptask.common.extension.getResponseResource
import com.example.ccfinancegrouptask.data.model.response.StockDescriptionResponseModel
import com.example.ccfinancegrouptask.data.model.response.StockListResponseModel
import com.example.ccfinancegrouptask.data.remote.datasource.StockRemoteDataSource
import com.example.ccfinancegrouptask.domain.repository.StockRepository

class StockRepositoryImpl constructor(private val stockRemoteDataSource: StockRemoteDataSource) : StockRepository {

    override suspend fun getStockList(): Resource<StockListResponseModel> {
        return stockRemoteDataSource.getStockList().getResponseResource()
    }

    override suspend fun getStockBySymbol(symbol: String): Resource<StockDescriptionResponseModel> {
        return stockRemoteDataSource.getStockDescription(symbol).getResponseResource()
    }

}