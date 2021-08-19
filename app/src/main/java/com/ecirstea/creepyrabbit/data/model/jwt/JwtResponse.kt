package com.ecirstea.creepyrabbit.data.model.jwt

import com.google.gson.annotations.SerializedName

data class JwtResponse(
    @SerializedName("token") val token: String
)