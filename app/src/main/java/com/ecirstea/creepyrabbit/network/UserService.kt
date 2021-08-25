package com.ecirstea.creepyrabbit.network

import android.util.Log
import com.ecirstea.creepyrabbit.core.RetrofitHelper
import com.ecirstea.creepyrabbit.data.model.jwt.JwtRequest
import com.ecirstea.creepyrabbit.data.model.jwt.JwtResponse
import com.ecirstea.creepyrabbit.data.model.user.User
import com.ecirstea.creepyrabbit.data.model.user.UserFeedback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "UserService"
class UserService {
    private val retrofit = RetrofitHelper.getApiClientForUsers()

    suspend fun authenticateUser(jwtRequest: JwtRequest) : JwtResponse? {

        return withContext(Dispatchers.IO) {
            Log.d(TAG, "authenticateUser: $jwtRequest")
            val response= retrofit.create(UserApiClient::class.java).authenticateUser(jwtRequest)
            Log.d(TAG, "authenticateUser: "+response.body())
            response.body()
        }
    }

    suspend fun getUserData(username: String): User?{
        return withContext(Dispatchers.IO){
            Log.d(TAG, "get username: $username")
            val response= retrofit.create(UserApiClient::class.java).getUserByUsername(username)
            Log.d(TAG, "get username: "+response.body())
            response.body()
        }
    }

    suspend fun updateUserData(user: User): User?{
        return withContext(Dispatchers.IO){
            val response= retrofit.create(UserApiClient::class.java).updateUser(user)
            Log.d(TAG, "updated user: "+response.body())
            response.body()
        }
    }

    suspend fun saveUserData(user: User): User?{
        return withContext(Dispatchers.IO){
            val response= retrofit.create(UserApiClient::class.java).saveUser(user)
            Log.d(TAG, "saved user: "+response.body())
            response.body()
        }
    }

    suspend fun sendUserMsj(msj: UserFeedback): String?{
        return withContext(Dispatchers.IO){
            val response= retrofit.create(UserApiClient::class.java).sendMsj(msj)
            response.body()
        }
    }

}