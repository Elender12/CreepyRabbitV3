package com.ecirstea.creepyrabbit.network

import com.ecirstea.creepyrabbit.data.model.jwt.JwtRequest
import com.ecirstea.creepyrabbit.data.model.jwt.JwtResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface JwtApiClient {
    @POST("/authenticate")
    suspend fun authenticateUser(@Body body: JwtRequest): Response<JwtResponse>
}