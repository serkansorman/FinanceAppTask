package com.example.ccfinancegrouptask.domain.usecase.base

import com.example.ccfinancegrouptask.common.AppDispatchers
import com.example.ccfinancegrouptask.common.Resource
import com.example.ccfinancegrouptask.domain.model.ErrorPrompt
import kotlinx.coroutines.withContext

abstract class SingleUseCase<in Params, Type>(private val dispatchers: AppDispatchers) {

    abstract suspend fun getExecutable(params: Params): Resource<Type>

    suspend operator fun invoke(params: Params): Resource<Type> {
        return try {
            withContext(dispatchers.io) {
                getExecutable(params)
            }
        } catch (e: Exception) {
            Resource.Failure(ErrorPrompt(errorCode = -1, message = e.message))
        }
    }
}