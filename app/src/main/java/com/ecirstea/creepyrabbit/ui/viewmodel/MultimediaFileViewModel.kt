package com.ecirstea.creepyrabbit.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ecirstea.creepyrabbit.data.model.multimedia.MultimediaData
import com.ecirstea.creepyrabbit.domain.GetAudiosUseCase
import com.ecirstea.creepyrabbit.network.FirestoreHelper


class MultimediaFileViewModel: ViewModel() {
  private val source = FirestoreHelper()

    fun getAudios() : LiveData<MutableList<MultimediaData>> {
        val mutableData = MutableLiveData<MutableList<MultimediaData>>()
        source.getAllAudioFiles().observeForever{ audios ->
            mutableData.value = audios
        }
        return mutableData
    }

    fun getAudiosByCategory(category: String): LiveData<MutableList<MultimediaData>>{
        val mutableData = MutableLiveData<MutableList<MultimediaData>>()
        source.getAudioFilesByCategory(category).observeForever{ audios ->
            mutableData.value = audios
        }
        return mutableData
    }
}