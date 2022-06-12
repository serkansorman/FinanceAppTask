package com.example.ccfinancegrouptask.common

import com.example.ccfinancegrouptask.domain.model.ErrorPrompt

sealed class Resource<out R> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure(val errorPrompt: ErrorPrompt) : Resource<Nothing>()
}