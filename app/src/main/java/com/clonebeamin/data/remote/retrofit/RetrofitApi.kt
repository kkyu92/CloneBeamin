package com.clonebeamin.data.remote.retrofit

import com.clonebeamin.data.remote.login.LoginInfo
import com.clonebeamin.data.remote.login.LoginDataItem
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitApi {
    @POST("api/token/")
    fun login(@Body loginInfo: LoginInfo): Single<LoginDataItem>
}