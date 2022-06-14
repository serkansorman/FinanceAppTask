package com.example.ccfinancegrouptask.common.extension

import com.example.ccfinancegrouptask.common.Constants
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

fun OkHttpClient.sendRequest(endPoint: String, params: Map<String, String> = emptyMap()): Response {
    val url = Constants.BASE_URL
        .plus(endPoint)
        .toHttpUrlOrNull()
        ?.newBuilder()?.apply {
            addQueryParameter(Constants.QUERY_REGION, Constants.REGION_US)
            params.forEach { (key, value) -> addQueryParameter(key, value) }
        }?.build()

    val request = Request.Builder()
        .url(url!!) //TODO null check
        .get()
        .build()

    return newCall(request).execute()
}