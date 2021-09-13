package com.clonebeamin.data.repository

import io.reactivex.Completable

class RepositoryImpl(
    private val localTokenDataSource: LocalTokenDataSource,
    private val loginDataSource: LoginDataSource
) : Repository {
    override fun login(id: String, password: String): Completable {
        return loginDataSource
            .login(id, password)
            .flatMapCompletable { loginDataItem ->
                localTokenDataSource
                    .deleteAllCachedToken()
                    .andThen(localTokenDataSource.saveToken(mappingRemoteDataToLocal(loginDataItem)))
            }
    }
}