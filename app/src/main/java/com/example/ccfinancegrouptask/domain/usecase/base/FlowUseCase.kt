package com.example.ccfinancegrouptask.domain.usecase.base

import com.example.ccfinancegrouptask.common.Resource
import com.example.ccfinancegrouptask.domain.model.ErrorPrompt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in Params, Type> {

    abstract suspend fun getFlow(params: Params): Flow<Resource<Type>>

    suspend operator fun invoke(params: Params): Flow<Resource<Type>> {
        return try {
            getFlow(params)
                .flowOn(Dispatchers.IO)
        } catch (e: Exception) {
            flowOf(Resource.Failure(ErrorPrompt(errorCode = -1, message = e.message)))
        }
    }
}