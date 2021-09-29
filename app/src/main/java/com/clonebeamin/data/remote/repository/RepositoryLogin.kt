package com.clonebeamin.data.remote.repository

import com.clonebeamin.data.local.token.LocalTokenDataSource
import com.clonebeamin.data.local.token.LocalTokenMapper.mappingRemoteDataToLocal
import com.clonebeamin.data.remote.login.LoginDataSource
import io.reactivex.Completable

class RepositoryLogin(
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