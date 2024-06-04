package com.example.futurefarmers.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.futurefarmers.data.remote.auth.AuthService
import com.example.futurefarmers.data.response.LoginResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository(private val authService: AuthService) {

    fun login(jsonObject: JsonObject): LiveData<LoginResponse> {
        val loginLiveData = MutableLiveData<LoginResponse>()

        authService.login(jsonObject).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val data = response.body()
                if (response.isSuccessful && data != null) {
                    loginLiveData.value = data
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e(MainRepository.TAG, "onFailure: ${t.message.toString()}")
            }
        })

        return loginLiveData
    }
    companion object {
        const val TAG="AuthRepository"
        @Volatile
        private var instance: AuthRepository? = null
        fun getInstance(
            authService: AuthService
        ): AuthRepository =
            instance ?: synchronized(this) {
                instance ?: AuthRepository(authService)
            }.also { instance = it }
    }
}