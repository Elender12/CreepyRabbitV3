package com.ecirstea.creepyrabbit.domain

import com.ecirstea.creepyrabbit.network.FirestoreHelper

class GetAudiosUseCase {
    private val helper = FirestoreHelper()
    operator fun invoke() = helper.getAllAudioFiles()
}