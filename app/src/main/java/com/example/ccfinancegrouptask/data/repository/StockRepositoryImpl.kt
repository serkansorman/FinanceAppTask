package com.example.ccfinancegrouptask.data.repository

import com.example.ccfinancegrouptask.common.Resource
import com.example.ccfinancegrouptask.common.extension.getResponseResource
import com.example.ccfinancegrouptask.data.model.response.StockListResponseModel
import com.example.ccfinancegrouptask.data.remote.datasource.StockRemoteDataSource
import com.example.ccfinancegrouptask.domain.repository.StockRepository
import javax.inject.Inject

class StockRepositoryImpl@Inject constructor(private val service: StockRemoteDataSource) : StockRepository {
    override suspend fun getStockList(): Resource<StockListResponseModel> {
        return service.getStockList().getResponseResource()
    }

    override fun getStockBySymbol() {
        TODO("Not yet implemented")
    }

}