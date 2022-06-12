package com.example.ccfinancegrouptask.domain.usecase

import com.example.ccfinancegrouptask.common.Resource
import com.example.ccfinancegrouptask.data.model.StockModel
import com.example.ccfinancegrouptask.domain.repository.StockRepository
import com.example.ccfinancegrouptask.domain.usecase.base.BaseUseCase
import javax.inject.Inject

class GetStockListUseCase @Inject constructor(
    private val stockRepository: StockRepository,
) : BaseUseCase<Unit, List<StockModel>>() { //TODO UI Model

    override suspend fun getExecutable(params: Unit): Resource<List<StockModel>> {
        return when(val result = stockRepository.getStockList()){
            is Resource.Success -> {
                //TODO Response model to UI Model
                Resource.Success(result.data.resultData.result)
            }
            is Resource.Failure -> {
                Resource.Failure(result.errorPrompt)
                //TODO ErrorModel to Error Prompt
            }
        }
    }
}