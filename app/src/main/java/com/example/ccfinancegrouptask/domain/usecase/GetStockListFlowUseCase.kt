package com.example.ccfinancegrouptask.domain.usecase

import com.example.ccfinancegrouptask.common.AppDispatchers
import com.example.ccfinancegrouptask.common.Resource
import com.example.ccfinancegrouptask.data.model.response.StockModel
import com.example.ccfinancegrouptask.domain.repository.StockRepository
import com.example.ccfinancegrouptask.domain.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetStockListFlowUseCase(
    private val stockRepository: StockRepository,
    private val dispatchers: AppDispatchers
) : FlowUseCase<Unit, List<StockModel>>(dispatchers) {

    override fun getFlow(params: Unit): Flow<Resource<List<StockModel>>> =
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