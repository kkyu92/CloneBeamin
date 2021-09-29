package com.clonebeamin.data.remote.repository

import io.reactivex.Completable

interface Repository {
    fun login(id: String, password: String): Completable
}