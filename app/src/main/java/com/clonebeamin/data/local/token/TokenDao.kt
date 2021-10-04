package com.clonebeamin.data.local.token

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface TokenDao {
    @Query(value = "SELECT * FROM token LIMIT 1")
    fun getToken(): Single<Token>

    @Insert
    fun saveToken(token: Token): Completable

    @Query(value = "DELETE FROM token")
    fun deleteAllCachedToken(): Completable
}