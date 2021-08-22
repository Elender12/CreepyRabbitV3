package com.ecirstea.creepyrabbit.domain

import com.ecirstea.creepyrabbit.data.model.jwt.JwtRequest
import com.ecirstea.creepyrabbit.data.repository.UserRepository

class GetJwtUseCase {
    private val repository = UserRepository()
    suspend operator fun invoke(jwtRequest: JwtRequest) = repository.getJwtToken(jwtRequest)
}