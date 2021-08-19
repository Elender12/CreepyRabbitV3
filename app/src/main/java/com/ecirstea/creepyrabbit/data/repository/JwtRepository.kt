package com.ecirstea.creepyrabbit.data.repository

import android.util.Log
import com.ecirstea.creepyrabbit.data.model.jwt.JwtRequest
import com.ecirstea.creepyrabbit.data.model.jwt.JwtResponse
import com.ecirstea.creepyrabbit.network.JwtService

private const val TAG = "JWTRepository"
class JwtRepository {
    private val jwtService = JwtService()
    suspend fun  getJwtToken(jwtRequest: JwtRequest): JwtResponse?{
        val response = jwtService.authenticateUser(jwtRequest)
        Log.d(TAG, "getJwtToken: $response")
        return  response
    }
}