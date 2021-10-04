package com.clonebeamin.data.local.token

import com.clonebeamin.data.remote.login.LoginResponse


object TokenMapper {
    fun mappingRemoteDataToLocal(loginResponse: LoginResponse): Token {
        return Token(
            accessToken = loginResponse.access,
            refreshToken = loginResponse.refresh
        )
    }
}