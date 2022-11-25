package com.example.ccfinancegrouptask.domain.usecase.base

import com.example.ccfinancegrouptask.common.Resource
import com.example.ccfinancegrouptask.domain.model.ErrorPrompt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class SingleUseCase<in Params, Type> {

    abstract suspend fun getExecutable(params: Params): Resource<Type>

    suspend operator fun invoke(params: Params): Resource<Type> {
        return try {
            withContext(Dispatchers.IO) {
                getExecutable(params)
            }
        } catch (e: Exception) {
            Resource.Failure(ErrorPrompt(errorCode = -1, message = e.message))
        }
    }
}