package com.ecirstea.creepyrabbit.domain

import com.ecirstea.creepyrabbit.data.repository.UserRepository

class GetUserByUsernameUseCase {
    private val repository = UserRepository()
    suspend operator fun invoke(username: String) = repository.getUserData(username)
}