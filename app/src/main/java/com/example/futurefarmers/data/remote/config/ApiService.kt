package com.example.futurefarmers.data.remote.config

import com.example.futurefarmers.data.response.ConfigResponse
import com.example.futurefarmers.data.response.DataResponse
import com.example.futurefarmers.data.response.GetLevelConfigResponse
import com.example.futurefarmers.data.response.GetPlantResponse
import com.example.futurefarmers.data.response.GetRelayConfigResponse
import com.example.futurefarmers.data.response.GetRelayStatusResponse
import com.example.futurefarmers.data.response.PostPlantResponse
import com.example.futurefarmers.data.response.RelayHistoryResponse
import com.example.futurefarmers.data.response.RelayResponse
import com.example.futurefarmers.data.response.UpdateLevelConfigResponse
import com.example.futurefarmers.data.response.UpdateRelayConfigResponse
import com.example.futurefarmers.data.response.UpdateRelayResponse
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ApiService {

    //dashboard
    @GET("api/v1/dashboard")
    fun getData(@Header("Authorization") value: String): Call<DataResponse>

    @GET("api/v1/dashboard")
    fun getDataObservable(@Header("Authorization") value: String): Observable<DataResponse>

    //plant
    @GET("api/v1/plant")
    fun getPlant(@Header("Authorization") value: String): Call<GetPlantResponse>

    @POST("api/v1/plant")
    fun postPlant(
        @Header("Authorization") value: String,
        @Body raw: JsonObject, ): Call<PostPlantResponse>

    //relay config
    @GET("api/v1/getrelayconfig")
    fun getRelayConfig(@Header("Authorization") value: String): Call<GetRelayConfigResponse>

    @PUT("api/v1/updaterelayconfig")
    fun updateRelayConfig(
        @Header("Authorization") value: String,
        @Body raw: JsonObject
    ): Call<UpdateRelayConfigResponse>

    //level config
    @GET("api/v1/getlevelconfig")
    fun getLevelConfig(@Header("Authorization") value: String): Call<GetLevelConfigResponse>

    @PUT("api/v1/updatelevelconfig")
    fun updateLevelConfig(
        @Header("Authorization") value: String,
        @Body raw: JsonObject
    ): Call<UpdateLevelConfigResponse>


    //update control panel\
    @GET("api/v1/getrelaystatus")
    fun getRelayStatus(@Header("Authorization") value: String): Observable<GetRelayStatusResponse>

    @PATCH("api/v1/updaterelayphup")
    fun updatRelayPhUp(
        @Header("Authorization") value: String,
        @Body raw: JsonObject
    ): Call<UpdateRelayResponse>

    @PATCH("api/v1/updaterelayphdown")
    fun updatRelayPhDown(
        @Header("Authorization") value: String,
        @Body raw: JsonObject
    ): Call<UpdateRelayResponse>

    @PATCH("api/v1/updaterelayfan")
    fun updatRelayFan(
        @Header("Authorization") value: String,
        @Body raw: JsonObject
    ): Call<UpdateRelayResponse>

    @PATCH("api/v1/updaterelaylight")
    fun updateRelayLight(
        @Header("Authorization") value: String,
        @Body raw: JsonObject
    ): Call<UpdateRelayResponse>

    @PATCH("api/v1/updaterelaynutrisi")
    fun updatRelayNutrisi(
        @Header("Authorization") value: String,
        @Body raw: JsonObject
    ): Call<UpdateRelayResponse>

    @PATCH("api/v1/updaterelaymanualone")
    fun updatRelayManualOne(
        @Header("Authorization") value: String,
        @Body raw: JsonObject
    ): Call<UpdateRelayResponse>

    @PATCH("api/v1/updaterelaymanualtwo")
    fun updateRelayManualTwo(
        @Header("Authorization") value: String,
        @Body raw: JsonObject
    ): Call<UpdateRelayResponse>

    @PATCH("api/v1/updaterelaymanualthree")
    fun updateRelayManualThree(
        @Header("Authorization") value: String,
        @Body raw: JsonObject
    ): Call<UpdateRelayResponse>

    @PATCH("api/v1/updaterelaymanualfive")
    fun updateRelayManualFive(
        @Header("Authorization") value: String,
        @Body raw: JsonObject
    ): Call<UpdateRelayResponse>

    @PATCH("api/v1/updaterelaymanualsix")
    fun updateRelayManualSix(
        @Header("Authorization") value: String,
        @Body raw: JsonObject
    ): Call<UpdateRelayResponse>

    @GET("api/v1/getrelayhistory")
    suspend fun getRelayHistory(
        @Query("page") page: Int
    ): RelayHistoryResponse
}
