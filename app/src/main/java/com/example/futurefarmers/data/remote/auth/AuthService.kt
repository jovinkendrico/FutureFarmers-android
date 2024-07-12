package com.example.futurefarmers.data.remote.auth

import com.example.futurefarmers.data.response.LoginResponse
import com.example.futurefarmers.data.response.RegisterResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {

    @POST("login")
    fun login(@Body raw: JsonObject): Call<LoginResponse>
    @POST("register")
    fun register(@Body raw: JsonObject): Call<RegisterResponse>
}