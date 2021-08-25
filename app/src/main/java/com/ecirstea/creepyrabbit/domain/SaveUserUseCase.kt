package com.ecirstea.creepyrabbit.domain

import com.ecirstea.creepyrabbit.data.model.user.User
import com.ecirstea.creepyrabbit.data.repository.UserRepository

class SaveUserUseCase {
    private val repository = UserRepository()
    suspend operator fun invoke(user: User) = repository.saveUserData(user)
}