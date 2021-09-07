package com.clonebeamin.common

import com.clonebeamin.network.ApiService
import com.clonebeamin.network.RetrofitClient

object Common {
    private const val BASE_URL =
        "https://r5670326j8.execute-api.ap-northeast-2.amazonaws.com/delivery_server/"

    val getApiService: ApiService
    get() = RetrofitClient.getRetrofitClient(BASE_URL).create(ApiService::class.java)
}