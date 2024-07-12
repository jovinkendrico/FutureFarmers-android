package com.example.futurefarmers.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.futurefarmers.data.repository.AuthRepository
import com.example.futurefarmers.data.response.RegisterResponse
import com.google.gson.JsonObject

class RegisterViewModel(private val repository: AuthRepository): ViewModel() {
    private lateinit var registerResponse: LiveData<RegisterResponse>
    fun register(jsonObject: JsonObject){
        registerResponse = repository.register(jsonObject)
    }
    fun getRegisterResponse(): LiveData<RegisterResponse>{
        return registerResponse
    }
}