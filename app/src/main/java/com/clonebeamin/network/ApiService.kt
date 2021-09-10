package com.clonebeamin.network

import com.clonebeamin.model.request.LoginRequest
import com.clonebeamin.model.response.LoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @POST("api/token/")
    fun login(
        @Body loginRequest: LoginRequest
    ): Single<LoginResponse>
}