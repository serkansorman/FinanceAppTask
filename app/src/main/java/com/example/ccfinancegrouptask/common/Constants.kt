package com.example.ccfinancegrouptask.common

object Constants {

    const val BASE_URL = "https://yh-finance.p.rapidapi.com/"
    const val STOCK_LIST_ENDPOINT = "market/v2/get-summary"
    const val STOCK_DESCRIPTION_ENDPOINT = "stock/v2/get-summary"
    const val REGION_US = "US"
    const val QUERY_REGION = "region"
    const val QUERY_SYMBOL = "symbol"
    const val STOCK_LIST_REFRESH_DELAY: Long = 8000
}