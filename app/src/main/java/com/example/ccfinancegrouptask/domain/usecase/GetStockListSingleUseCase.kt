package com.example.ccfinancegrouptask.domain.usecase

import com.example.ccfinancegrouptask.common.AppDispatchers
import com.example.ccfinancegrouptask.common.Resource
import com.example.ccfinancegrouptask.data.model.response.StockModel
import com.example.ccfinancegrouptask.domain.repository.StockRepository
import com.example.ccfinancegrouptask.domain.usecase.base.SingleUseCase

class GetStockListSingleUseCase(
    private val stockRepository: StockRepository,
    private val dispatchers: AppDispatchers
) : SingleUseCase<Unit, List<StockModel>>(dispatchers) {

    override suspend fun getExecutable(params: Unit): Resource<List<StockModel>> {
        return when (val result = stockRepository.getSingleStockList()) {
            is Resource.Success -> {
                Resource.Success(result.data.resultData.result)
            }
            is Resource.Failure -> {
                Resource.Failure(result.errorPrompt)
            }
        }
    }
}