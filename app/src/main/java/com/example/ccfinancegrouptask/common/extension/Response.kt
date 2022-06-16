package com.example.ccfinancegrouptask.common.extension

import com.example.ccfinancegrouptask.common.Resource
import com.example.ccfinancegrouptask.data.model.response.StockListResponseModel
import com.example.ccfinancegrouptask.domain.model.ErrorPrompt
import com.google.gson.Gson
import okhttp3.Response


fun Response?.getPrompt() = ErrorPrompt(
    errorCode = this?.code,
    message = this?.message
)

fun Response.isSuccess(): Boolean =
    isSuccessful && body != null

inline fun <reified T> Response.getResponseResource(): Resource<T> {
    return if (isSuccess()) {
        val response = Gson().fromJson(body!!.string(), T::class.java)
        Resource.Success(response)
    } else
        Resource.Failure(this.getPrompt())
}