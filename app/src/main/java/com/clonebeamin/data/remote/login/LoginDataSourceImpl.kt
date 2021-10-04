package com.clonebeamin.data.remote.login

import com.clonebeamin.data.remote.retrofit.ApiService
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : LoginDataSource {
    override fun login(id: String, password: String): Single<LoginResponse> {
        return apiService
            .login(LoginRequest(id, password))
            .subscribeOn(Schedulers.io())
    }
}