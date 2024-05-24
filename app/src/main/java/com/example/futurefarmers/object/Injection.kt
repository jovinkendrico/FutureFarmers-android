package com.example.futurefarmers.`object`

import android.content.Context
import com.example.futurefarmers.data.remote.config.ApiConfig
import com.example.futurefarmers.data.repository.MainRepository

object Injection {
    fun provideMainRepository(context: Context): MainRepository{
        val apiService = ApiConfig.getApiService()
        return MainRepository.getInstance(apiService)
    }
}