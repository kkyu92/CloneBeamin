package com.clonebeamin.repository

import com.clonebeamin.data.login.LoginDataItem
import com.clonebeamin.data.login.LoginInfo
import com.clonebeamin.network.RetrofitClient
import io.reactivex.Single

class LoginRepository {
    fun login(id: String, pw: String): Single<LoginDataItem> {
        return RetrofitClient.getApiService.login(LoginInfo(id, pw))
    }
}