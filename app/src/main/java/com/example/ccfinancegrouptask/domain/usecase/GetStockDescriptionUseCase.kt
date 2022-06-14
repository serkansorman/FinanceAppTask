package com.example.ccfinancegrouptask.domain.usecase

import com.example.ccfinancegrouptask.common.Resource
import com.example.ccfinancegrouptask.data.model.response.StockDescriptionResponseModel
import com.example.ccfinancegrouptask.domain.repository.StockRepository
import com.example.ccfinancegrouptask.domain.usecase.base.BaseUseCase

class GetStockDescriptionUseCase(
    private val stockRepository: StockRepository,
) : BaseUseCase<GetStockDescriptionUseCase.Param, StockDescriptionResponseModel>() { //TODO UI Model

    override suspend fun getExecutable(params: Param): Resource<StockDescriptionResponseModel> {
        return when(val result = stockRepository.getStockBySymbol(params.symbol.orEmpty())){//TODO orempty
            is Resource.Success -> {
                //TODO Response model to UI Model
                Resource.Success(result.data)
            }
            is Resource.Failure -> {
                Resource.Failure(result.errorPrompt)
                //TODO ErrorModel to Error Prompt
            }
        }
    }

    data class Param(val symbol: String?)
}