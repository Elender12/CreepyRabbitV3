package com.ecirstea.creepyrabbit.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecirstea.creepyrabbit.data.model.jwt.JwtRequest
import com.ecirstea.creepyrabbit.data.model.jwt.JwtResponse
import com.ecirstea.creepyrabbit.domain.GetJwtUseCase
import kotlinx.coroutines.launch

private const val TAG = "JWTViewModel"
class JwtViewModel: ViewModel() {
    val jwtModel = MutableLiveData<JwtResponse?>()
    var getJwtUseCase = GetJwtUseCase()

    fun login(username:  String, password: String) {
        viewModelScope.launch {
            val jwtRequest = JwtRequest(username, password)
            //     isLoading.postValue(true)
            val result = getJwtUseCase(jwtRequest)
            Log.d(TAG, "onCreate: result is: $result")
            jwtModel.postValue(result)
        }
    }
}