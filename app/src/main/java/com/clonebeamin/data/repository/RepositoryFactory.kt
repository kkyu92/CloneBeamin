package com.clonebeamin.data.repository

import android.content.Context
import com.clonebeamin.network.RetrofitClient

class RepositoryFactory {
    companion object {
        @Volatile
        private var INSTANCE: Repository? = null

        fun getRepository(context: Context): Repository {
            return INSTANCE ?: synchronized(this) {
                val instance = RepositoryImpl(
                    localTokenDataSource = LocalTokenDataSourceImpl(
                        localTokenDao = AppDatabase.getDatabase(context).tokenDao()
                    ),
                    loginDataSource = LoginDataSourceImpl(
                        loginApi = RetrofitClient
                    )
                )
                INSTANCE = instance
                instance
            }
        }
    }
}