package com.ecirstea.creepyrabbit.data.model.jwt

import com.google.gson.annotations.SerializedName

data class JwtRequest(

    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String
)