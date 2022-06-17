package com.example.ccfinancegrouptask.domain.usecase

import com.example.ccfinancegrouptask.common.Resource
import com.example.ccfinancegrouptask.data.model.response.StockDescriptionResponseModel
import com.example.ccfinancegrouptask.domain.mapper.StockDescriptionUIMapper
import com.example.ccfinancegrouptask.domain.model.StockDescriptionUIModel
import com.example.ccfinancegrouptask.domain.repository.StockRepository
import com.example.ccfinancegrouptask.domain.usecase.base.BaseUseCase

class GetStockDescriptionUseCase(
    private val stockRepository: StockRepository,
    private val stockDescriptionUIMapper: StockDescriptionUIMapper
) : BaseUseCase<GetStockDescriptionUseCase.Param, StockDescriptionUIModel>() {

    override suspend fun getExecutable(params: Param): Resource<StockDescriptionUIModel> {
        return when(val result = stockRepository.getStockBySymbol(params.symbol.orEmpty())){
            is Resource.Success -> {
                Resource.Success(stockDescriptionUIMapper.mapToUI(result.data))
            }
            is Resource.Failure -> {
                Resource.Failure(result.errorPrompt)
            }
        }
    }

    data class Param(val symbol: String?)
}