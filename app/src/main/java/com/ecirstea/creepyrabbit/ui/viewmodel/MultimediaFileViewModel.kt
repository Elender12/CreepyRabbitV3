package com.ecirstea.creepyrabbit.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ecirstea.creepyrabbit.data.model.multimedia.MultimediaData
import com.ecirstea.creepyrabbit.domain.GetAudiosUseCase
import com.ecirstea.creepyrabbit.network.FirestoreHelper

private val TAG = "MultimediaFlleViewModel"
class MultimediaFileViewModel: ViewModel() {
  //  val results = MutableLiveData<MutableList<MultimediaData>>()
  private val source = FirestoreHelper()
  //  var getAudiosUseCase = GetAudiosUseCase()


/*    fun getAudios() {
        viewModelScope.launch {

            val result = getAudiosUseCase()
            Log.d(TAG, "onCreate MultimediaFileViewModel: result is: $result")
            results.postValue(result as MutableList<MultimediaData>?)
        }
    }*/


    fun getAudios() : LiveData<MutableList<MultimediaData>> {
        val mutableData = MutableLiveData<MutableList<MultimediaData>>()
        source.getAllAudioFiles().observeForever{ audios ->
            mutableData.value = audios
        }
        return mutableData
    }
}