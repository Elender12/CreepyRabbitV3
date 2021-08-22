package com.ecirstea.creepyrabbit.data.repository

import android.util.Log
import com.ecirstea.creepyrabbit.data.model.jwt.JwtRequest
import com.ecirstea.creepyrabbit.data.model.jwt.JwtResponse
import com.ecirstea.creepyrabbit.data.model.user.User
import com.ecirstea.creepyrabbit.network.UserService

private const val TAG = "UserRepository"
class UserRepository {

    private val userService = UserService()

    suspend fun  getJwtToken(jwtRequest: JwtRequest): JwtResponse?{
        val response = userService.authenticateUser(jwtRequest)
        Log.d(TAG, "getJwtToken: $response")
        return  response
    }

    suspend fun  getUserData(username: String): User?{
        val response = userService.getUserData(username)
        Log.d(TAG, "getUserData BY Username" +
                ": $response")
        return response
    }

    suspend fun  updateUserData(user: User): User?{
        val response = userService.updateUserData(user)
        Log.d(TAG, "update user" +
                ": $response")
        return response
    }
}
