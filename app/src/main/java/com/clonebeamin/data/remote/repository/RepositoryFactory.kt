package com.clonebeamin.data.remote.repository

import android.content.Context
import com.clonebeamin.data.local.AppDatabase
import com.clonebeamin.data.local.token.LocalTokenDataSourceImpl
import com.clonebeamin.data.remote.login.LoginDataSourceImpl
import com.clonebeamin.data.remote.retrofit.RetrofitClient

class RepositoryFactory {
    companion object {
        @Volatile
        private var INSTANCE: Repository? = null

        fun getRepository(context: Context): Repository {
            return INSTANCE ?: synchronized(this) {
                val instance = RepositoryLogin(
                    localTokenDataSource = LocalTokenDataSourceImpl(
                        localTokenDao = AppDatabase.getDatabase(context).tokenDao()
                    ),
                    loginDataSource = LoginDataSourceImpl(
                        api = RetrofitClient.loginAPI
                    )
                )
                INSTANCE = instance
                instance
            }
        }
    }
}