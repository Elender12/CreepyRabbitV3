package com.ecirstea.creepyrabbit.domain

import com.ecirstea.creepyrabbit.data.model.user.User
import com.ecirstea.creepyrabbit.data.repository.UserRepository

class UpdateUserUseCase {
    private val repository = UserRepository()
    suspend operator fun invoke(user: User) = repository.updateUserData(user)
}