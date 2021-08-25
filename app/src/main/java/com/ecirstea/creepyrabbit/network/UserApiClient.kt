package com.ecirstea.creepyrabbit.network

import com.ecirstea.creepyrabbit.data.model.jwt.JwtRequest
import com.ecirstea.creepyrabbit.data.model.jwt.JwtResponse
import com.ecirstea.creepyrabbit.data.model.user.User
import com.ecirstea.creepyrabbit.data.model.user.UserFeedback
import retrofit2.Response
import retrofit2.http.*

interface UserApiClient {
    @POST("/authenticate")
    @Headers("No-Authentication: true")
    suspend fun authenticateUser(@Body body: JwtRequest): Response<JwtResponse>

    @POST("/users")
    @Headers("No-Authentication: true")
    suspend fun saveUser(@Body user: User): Response<User>

    @GET("users/by/{username}")
    suspend fun getUserByUsername(@Path("username") username: String): Response<User>

    @PUT("/users")
    suspend fun updateUser(@Body user: User) : Response<User>

    @POST("/contactus")
    suspend fun sendMsj(@Body feedback: UserFeedback): Response<String>

}