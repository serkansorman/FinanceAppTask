package com.example.ccfinancegrouptask.data.repository

import com.example.ccfinancegrouptask.common.Constants
import com.example.ccfinancegrouptask.common.Resource
import com.example.ccfinancegrouptask.common.extension.getResponseResource
import com.example.ccfinancegrouptask.data.model.response.StockDescriptionResponseModel
import com.example.ccfinancegrouptask.data.model.response.StockListResponseModel
import com.example.ccfinancegrouptask.data.remote.datasource.StockRemoteDataSource
import com.example.ccfinancegrouptask.domain.repository.StockRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StockRepositoryImpl constructor(
    private val stockRemoteDataSource: StockRemoteDataSource,
) :
    StockRepository {

    override fun getStockListFlow(): Flow<Resource<StockListResponseModel>> = flow {
        while (true) {
            emit(stockRemoteDataSource.getStockList().getResponseResource())
            delay(Constants.STOCK_LIST_REFRESH_INTERVAL_MS)
        }
    }

    override suspend fun getStockBySymbol(symbol: String): Resource<StockDescriptionResponseModel> {
        return stockRemoteDataSource.getStockDescription(symbol).getResponseResource()
    }

}