package com.ecirstea.creepyrabbit.data.model.user

data class User(
    val id: String = "",
    val name: String = "",
    val username: String = "",
    var password: String = "",
    var email: String = "",
    val disabled: Boolean = false,
    val created: String = "",
    val modified: String = ""
)