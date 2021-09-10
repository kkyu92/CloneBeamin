package com.clonebeamin.network

import com.clonebeamin.data.login.LoginInfo
import com.clonebeamin.data.login.LoginDataItem
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/token/")
    fun login(
        @Body loginInfo: LoginInfo
    ): Single<LoginDataItem>
}