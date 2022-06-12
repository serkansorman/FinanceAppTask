package com.example.ccfinancegrouptask.data.repository

import com.example.ccfinancegrouptask.data.model.response.StockListResponseModel
import com.example.ccfinancegrouptask.data.remote.datasource.StockRemoteDataSource
import com.example.ccfinancegrouptask.domain.repository.StockRepository
import javax.inject.Inject

class StockRepositoryImpl@Inject constructor(private val service: StockRemoteDataSource) : StockRepository {
    override suspend fun getStockList(): StockListResponseModel {
        return service.getStockList()
    }

    override fun getStockBySymbol() {
        TODO("Not yet implemented")
    }

}