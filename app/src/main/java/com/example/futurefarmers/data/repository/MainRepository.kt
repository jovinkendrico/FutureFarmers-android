package com.example.futurefarmers.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.futurefarmers.data.remote.config.ApiService
import com.example.futurefarmers.data.response.DataResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository(private val apiService: ApiService) {
    fun getData(): LiveData<DataResponse> {
        val dashboardLiveData = MutableLiveData<DataResponse>()

        apiService.getData().enqueue(object : Callback<DataResponse> {
            override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                val data = response.body()
                if (response.isSuccessful && data != null) {
                    dashboardLiveData.value = data
                }
            }

            override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })

        return dashboardLiveData
    }
    companion object {
        const val TAG="MainRepository"
        @Volatile
        private var instance: MainRepository? = null
        fun getInstance(
            apiService: ApiService,
        ): MainRepository =
            instance ?: synchronized(this) {
                instance ?: MainRepository(apiService)
            }.also { instance = it }
    }
}