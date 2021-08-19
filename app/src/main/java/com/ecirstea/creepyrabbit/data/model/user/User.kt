package com.ecirstea.creepyrabbit.data.model.user

data class User(
    val id: String,
    val name: String,
    val username: String,
    val password: String,
    val email: String,
    val disabled: Boolean,
    val created: String,
    val modified: String
)