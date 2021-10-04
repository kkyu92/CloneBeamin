package com.clonebeamin.data.remote.repository

import com.clonebeamin.data.local.token.TokenDataSource
import com.clonebeamin.data.local.token.TokenMapper.mappingRemoteDataToLocal
import com.clonebeamin.data.remote.login.LoginDataSource
import io.reactivex.Completable
import javax.inject.Inject

class RepositoryLogin @Inject constructor(
    private val tokenDataSource: TokenDataSource,
    private val loginDataSource: LoginDataSource
) : Repository {
    override fun login(id: String, password: String): Completable {
        return loginDataSource
            .login(id, password)
            .flatMapCompletable { loginDataItem ->
                tokenDataSource
                    .deleteAllCachedToken()
                    .andThen(tokenDataSource.saveToken(mappingRemoteDataToLocal(loginDataItem)))
            }
    }
}