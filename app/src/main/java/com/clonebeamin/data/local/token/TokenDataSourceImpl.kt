package com.clonebeamin.data.local.token

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TokenDataSourceImpl @Inject constructor(
    private val tokenDao: TokenDao
) : TokenDataSource {
    override fun getToken(): Single<Token> {
        return tokenDao
            .getToken()
            .subscribeOn(Schedulers.io())
    }

    override fun saveToken(token: Token): Completable {
        return tokenDao
            .saveToken(token)
            .subscribeOn(Schedulers.io())
    }

    override fun deleteAllCachedToken(): Completable {
        return tokenDao
            .deleteAllCachedToken()
            .subscribeOn(Schedulers.io())
    }
}