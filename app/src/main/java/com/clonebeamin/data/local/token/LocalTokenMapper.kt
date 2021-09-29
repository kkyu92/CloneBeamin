package com.clonebeamin.data.local.token

import com.clonebeamin.data.remote.login.LoginDataItem


object LocalTokenMapper {
    fun mappingRemoteDataToLocal(loginDataItem: LoginDataItem): LocalTokenItem {
        return LocalTokenItem(
            accessToken = loginDataItem.access,
            refreshToken = loginDataItem.refresh
        )
    }
}