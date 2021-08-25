package com.ecirstea.creepyrabbit.domain

import com.ecirstea.creepyrabbit.data.model.user.UserFeedback
import com.ecirstea.creepyrabbit.data.repository.UserRepository

class SendUserFeedbackUseCase {
    private val repository = UserRepository()
    suspend operator fun invoke(msj: UserFeedback) = repository.sendUserMsj(msj)
}