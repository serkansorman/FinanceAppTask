package com.example.ccfinancegrouptask.domain.usecase

import com.example.ccfinancegrouptask.common.Resource
import com.example.ccfinancegrouptask.data.model.response.StockModel
import com.example.ccfinancegrouptask.domain.repository.StockRepository
import com.example.ccfinancegrouptask.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetStockListUseCase(
    private val stockRepository: StockRepository,
) : FlowUseCase<Unit, List<StockModel>>() {

    override suspend fun getFlow(params: Unit): Flow<Resource<List<StockModel>>> =
        stockRepository.getStockListFlow()
            .map { result ->
                when (result) {
                    is Resource.Success -> {
                        Resource.Success(result.data.resultData.result)
                    }
                    is Resource.Failure -> {
                        Resource.Failure(result.errorPrompt)
                    }
                }
            }
}