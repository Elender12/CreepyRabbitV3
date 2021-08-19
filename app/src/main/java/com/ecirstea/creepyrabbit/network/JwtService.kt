package com.ecirstea.creepyrabbit.network

import android.util.Log
import com.ecirstea.creepyrabbit.core.RetrofitHelper
import com.ecirstea.creepyrabbit.data.model.jwt.JwtRequest
import com.ecirstea.creepyrabbit.data.model.jwt.JwtResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "JWTService"
class JwtService {
    private val retrofit = RetrofitHelper.getApiClientForUsers()

    suspend fun authenticateUser(jwtRequest: JwtRequest) : JwtResponse? {

        return withContext(Dispatchers.IO) {
            Log.d(TAG, "authenticateUser: $jwtRequest")
            val response= retrofit.create(JwtApiClient::class.java).authenticateUser(jwtRequest)
            Log.d(TAG, "authenticateUser: "+response.body())
            response.body()
        }
    }
}