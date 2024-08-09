package com.project.gramsoolra.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.gramsoolra.common.Resource
import com.project.gramsoolra.repositories.MainRepository
import com.project.gramsoolra.request.LoginRequest
import com.project.gramsoolra.response.LoginResponse
import kotlinx.coroutines.launch

class ApiViewModel (
    private val mainRepository: MainRepository
) : ViewModel()
{
       private val _res_login = MutableLiveData<Resource<LoginResponse>>()
        val res_login: LiveData<Resource<LoginResponse>> get() = _res_login

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            _res_login.postValue(Resource.loading(null))
            _res_login.postValue(mainRepository.login(loginRequest))
        }
    }

}