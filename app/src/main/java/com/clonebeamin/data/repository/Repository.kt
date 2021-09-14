package com.clonebeamin.data.repository

import io.reactivex.Completable

interface Repository {
    fun login(id: String, password: String): Completable
}