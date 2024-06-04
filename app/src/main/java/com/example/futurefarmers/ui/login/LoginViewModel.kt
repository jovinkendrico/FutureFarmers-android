package com.example.futurefarmers.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.futurefarmers.data.repository.AuthRepository
import com.example.futurefarmers.data.repository.MainRepository
import com.example.futurefarmers.data.response.LoginResponse
import com.google.gson.JsonObject

class LoginViewModel(private val repository: AuthRepository):ViewModel() {
    private lateinit var loginResponse: LiveData<LoginResponse>

    fun login(jsonObject: JsonObject){
        loginResponse = repository.login(jsonObject)
    }
    fun getLoginResponse(): LiveData<LoginResponse>{
        return loginResponse
    }
}