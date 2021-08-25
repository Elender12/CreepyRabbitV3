package com.ecirstea.creepyrabbit.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecirstea.creepyrabbit.data.model.user.User
import com.ecirstea.creepyrabbit.data.model.user.UserFeedback
import com.ecirstea.creepyrabbit.domain.GetUserByUsernameUseCase
import com.ecirstea.creepyrabbit.domain.SaveUserUseCase
import com.ecirstea.creepyrabbit.domain.SendUserFeedbackUseCase
import com.ecirstea.creepyrabbit.domain.UpdateUserUseCase
import kotlinx.coroutines.launch
private const val TAG = "UserViewModel"
class UserViewModel: ViewModel(){
    val userModel = MutableLiveData<User?>()
    val userResponse = MutableLiveData<String?>()
    var getUserUseCase = GetUserByUsernameUseCase()
    val updateUserUseCase = UpdateUserUseCase()
    val sendUserFeedback = SendUserFeedbackUseCase()
    val saveUserUseCase = SaveUserUseCase()

    fun getUserByUsername(username:  String) {
        viewModelScope.launch {
            val result = getUserUseCase(username)
            Log.d(TAG, "onCreate: result is: $result")
            userModel.postValue(result)
        }
    }

    fun updateUser(user:  User) {
        viewModelScope.launch {
            val result = updateUserUseCase(user)
            Log.d(TAG, "onCreate: result is: $result")
            userModel.postValue(result)
        }
    }

    fun saveUser(user:  User) {
        viewModelScope.launch {
            val result = saveUserUseCase(user)
            Log.d(TAG, "onCreate: result is: $result")
            userModel.postValue(result)
        }
    }

    fun sendUserMsj(msj: UserFeedback){
        viewModelScope.launch {
            val result = sendUserFeedback(msj)
            userResponse.postValue(result)
        }
    }
}