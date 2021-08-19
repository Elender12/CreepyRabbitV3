package com.ecirstea.creepyrabbit.domain

import com.ecirstea.creepyrabbit.data.model.jwt.JwtRequest
import com.ecirstea.creepyrabbit.data.repository.JwtRepository

class GetJwtUseCase {
    private val repository = JwtRepository()
    suspend operator fun invoke(jwtRequest: JwtRequest) = repository.getJwtToken(jwtRequest)
}