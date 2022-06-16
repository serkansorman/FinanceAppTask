package com.example.ccfinancegrouptask.domain.mapper

import com.example.ccfinancegrouptask.data.model.response.StockDescriptionResponseModel
import com.example.ccfinancegrouptask.domain.model.StockDescriptionUIModel

class StockDescriptionUIMapper {
    fun mapToUI(responseModel: StockDescriptionResponseModel) =
        StockDescriptionUIModel(
            shortName = responseModel.summary?.shortName,
            currency = responseModel.summary?.currency,
            lastPrice = responseModel.summary?.regularMarketPrice?.fmt,
            changePercent = responseModel.summary?.regularMarketChangePercent?.fmt,
            openPrice = responseModel.summary?.regularMarketOpen?.fmt,
            closePrice = responseModel.summary?.regularMarketPreviousClose?.fmt,
            priceLow = responseModel.summary?.regularMarketDayLow?.fmt,
            priceHigh = responseModel.summary?.regularMarketDayHigh?.fmt
        )
}