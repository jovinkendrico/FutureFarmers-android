package com.example.futurefarmers.data.remote.config

import com.example.futurefarmers.data.response.ConfigResponse
import com.example.futurefarmers.data.response.DataResponse
import com.example.futurefarmers.data.response.GetPlantResponse
import com.example.futurefarmers.data.response.GetRelayConfigResponse
import com.example.futurefarmers.data.response.PostPlantResponse
import com.example.futurefarmers.data.response.RelayResponse
import com.example.futurefarmers.data.response.UpdateRelayConfigResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface ApiService {

    //dashboard
    @GET("api/v1/dashboard")
    fun getData(): Call<DataResponse>

    //plant
    @GET("api/v1/plant")
    fun getPlant(): Call<GetPlantResponse>
    @POST("api/v1/plant")
    fun postPlant(@Body raw: JsonObject): Call<PostPlantResponse>

    //relay config
    @GET("api/v1/getrelayconfig")
    fun getRelayConfig(): Call<GetRelayConfigResponse>
    @PUT("api/v1/updaterelayconfig")
    fun updateRelayConfig(@Body raw: JsonObject): Call<UpdateRelayConfigResponse>

}