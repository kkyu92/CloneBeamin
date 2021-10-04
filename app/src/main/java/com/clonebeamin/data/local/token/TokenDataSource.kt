package com.clonebeamin.data.local.token

import io.reactivex.Completable
import io.reactivex.Single

interface TokenDataSource {
    fun getToken(): Single<Token>
    fun saveToken(token: Token): Completable
    fun deleteAllCachedToken(): Completable
}