package com.project.gramsoolra.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.gramsoolra.repositories.MainRepository

class ViewModelFactory (private val mainRepository: MainRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ApiViewModel(mainRepository) as T
    }

}