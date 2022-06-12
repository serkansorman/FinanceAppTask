package com.example.ccfinancegrouptask.common.extension

import com.example.ccfinancegrouptask.common.Resource
import com.example.ccfinancegrouptask.domain.model.ErrorPrompt
import retrofit2.Response



fun <T> Response<out T>?.getPrompt() = ErrorPrompt(
    errorCode = this?.code(),
    message = this?.message()
)

fun <T> Response<T>.isSuccess(): Boolean =
    isSuccessful && body() != null

fun <T> Response<T>.getResponseResource(): Resource<T> {
    return if (isSuccess())
        Resource.Success(body()!!)
    else
        Resource.Failure(this.getPrompt())
}