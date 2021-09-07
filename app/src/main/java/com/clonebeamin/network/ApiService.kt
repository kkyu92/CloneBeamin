package com.clonebeamin.network

import com.clonebeamin.model.User
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("api/token/")
    fun login(
        @Field("username") username: String?,
        @Field("password") password: String?
    ): Single<User>
}