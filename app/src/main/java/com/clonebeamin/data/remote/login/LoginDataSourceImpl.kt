package com.clonebeamin.data.remote.login

import com.clonebeamin.data.remote.retrofit.RetrofitApi
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class LoginDataSourceImpl(
    private val api: RetrofitApi
) : LoginDataSource {
    override fun login(id: String, password: String): Single<LoginDataItem> {
        return api
            .login(LoginInfo(id, password))
            .subscribeOn(Schedulers.io())
    }
}