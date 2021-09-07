package com.clonebeamin.network

import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.POST

interface ApiService {
    @POST("api/token/")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Single<LoginModel>
}