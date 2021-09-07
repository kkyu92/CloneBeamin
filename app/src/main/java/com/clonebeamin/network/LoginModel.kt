package com.clonebeamin.network

import com.google.gson.annotations.SerializedName

data class LoginModel (
    @SerializedName("refresh") var refresh: String,
    @SerializedName("access") var access: String
)