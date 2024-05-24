package com.example.futurefarmers.data.remote.config

import com.example.futurefarmers.data.response.DataResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("api/v1/dashboard")
    fun getData(): Call<DataResponse>
}