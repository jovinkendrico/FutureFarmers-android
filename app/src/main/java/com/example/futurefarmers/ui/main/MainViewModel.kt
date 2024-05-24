package com.example.futurefarmers.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.futurefarmers.data.repository.MainRepository
import com.example.futurefarmers.data.response.DataResponse

class MainViewModel(private val repository: MainRepository): ViewModel(){
    companion object{
        const val TAG ="MainViewModel"
    }

    val dataDashboard: LiveData<DataResponse> = repository.getData()

}