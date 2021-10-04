package com.clonebeamin.data.remote.retrofit

import com.clonebeamin.data.remote.login.LoginRequest
import com.clonebeamin.data.remote.login.LoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/token/")
    fun login(@Body loginRequest: LoginRequest): Single<LoginResponse>
}