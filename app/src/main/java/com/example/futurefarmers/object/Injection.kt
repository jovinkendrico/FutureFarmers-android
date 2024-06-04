package com.example.futurefarmers.`object`

import android.content.Context
import com.example.futurefarmers.data.remote.auth.AuthConfig
import com.example.futurefarmers.data.remote.config.ApiConfig
import com.example.futurefarmers.data.repository.AuthRepository
import com.example.futurefarmers.data.repository.MainRepository

object Injection {
    fun provideMainRepository(context: Context): MainRepository{
        val apiService = ApiConfig.getApiService()
        return MainRepository.getInstance(apiService)
    }

    fun provideAuthRepository(context: Context): AuthRepository{
        val authService = AuthConfig.getApiService()
        return AuthRepository.getInstance(authService)
    }
}