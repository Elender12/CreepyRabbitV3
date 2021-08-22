package com.ecirstea.creepyrabbit.network

import com.ecirstea.creepyrabbit.data.model.jwt.JwtRequest
import com.ecirstea.creepyrabbit.data.model.jwt.JwtResponse
import com.ecirstea.creepyrabbit.data.model.user.User
import retrofit2.Response
import retrofit2.http.*

interface UserApiClient {
    @POST("/authenticate")
    @Headers("No-Authentication: true")
    suspend fun authenticateUser(@Body body: JwtRequest): Response<JwtResponse>

    @GET("users/by/{username}")
    suspend fun getUserByUsername(@Path("username") username: String): Response<User>

    @PUT("/users")
    suspend fun updateUser(@Body user: User) : Response<User>

}