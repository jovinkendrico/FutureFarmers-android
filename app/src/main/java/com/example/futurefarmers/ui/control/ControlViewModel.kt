package com.example.futurefarmers.ui.control

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.futurefarmers.data.repository.MainRepository
import com.example.futurefarmers.data.response.PostPlantResponse
import com.example.futurefarmers.data.response.UpdateRelayResponse
import com.google.gson.JsonObject

class ControlViewModel(private var repository: MainRepository): ViewModel(){

    private lateinit var relayFanResponse: LiveData<UpdateRelayResponse>
    private lateinit var relayLightResponse: LiveData<UpdateRelayResponse>
    private lateinit var relayPhUpResponse: LiveData<UpdateRelayResponse>
    private lateinit var relayPhDownResponse: LiveData<UpdateRelayResponse>
    private lateinit var relayNutrisiResponse: LiveData<UpdateRelayResponse>

    private lateinit var relayManualOneResponse: LiveData<UpdateRelayResponse>
    private lateinit var relayManualTwoResponse: LiveData<UpdateRelayResponse>
    private lateinit var relayManualThreeResponse: LiveData<UpdateRelayResponse>
    private lateinit var relayManualFiveResponse: LiveData<UpdateRelayResponse>
    private lateinit var relayManualSixResponse: LiveData<UpdateRelayResponse>

    fun getSession(): LiveData<String> {
        return repository.getSession().asLiveData()
    }

    fun updateRelayFan(token : String, jsonObject: JsonObject){
        relayFanResponse = repository.updateRelayFan(token,jsonObject)
    }
    fun getRelayFanResponse(): LiveData<UpdateRelayResponse> {
        return relayFanResponse
    }

    fun updateRelayLight(token : String, jsonObject: JsonObject){
        relayLightResponse = repository.updateRelayLight(token,jsonObject)
    }
    fun getRelayLightResponse(): LiveData<UpdateRelayResponse> {
        return relayLightResponse
    }

    fun updateRelayPhUp(token : String, jsonObject: JsonObject){
        relayPhUpResponse = repository.updateRelayPhUp(token,jsonObject)
    }
    fun getRelayPhUpRespoonse(): LiveData<UpdateRelayResponse> {
        return relayPhUpResponse
    }

    fun updateRelayPhDown(token : String, jsonObject: JsonObject){
        relayPhDownResponse = repository.updateRelayPhDown(token,jsonObject)
    }
    fun getRelayPhDownResponse(): LiveData<UpdateRelayResponse> {
        return relayPhDownResponse
    }

    fun updateRelayNutrisi(token : String, jsonObject: JsonObject){
        relayNutrisiResponse = repository.updateRelayNutrisi(token,jsonObject)
    }
    fun getRelayNutrisiResponse(): LiveData<UpdateRelayResponse> {
        return relayNutrisiResponse
    }
    fun updateRelayManualOne(token : String, jsonObject: JsonObject){
        relayManualOneResponse = repository.updateRelayManualOne(token,jsonObject)
    }
    fun getRelayManualOneResponse(): LiveData<UpdateRelayResponse> {
        return relayManualOneResponse
    }

    fun updateRelayManualTwo(token : String, jsonObject: JsonObject){
        relayManualTwoResponse = repository.updateRelayManualTwo(token,jsonObject)
    }
    fun getRelayManualTwoResponse(): LiveData<UpdateRelayResponse> {
        return relayManualTwoResponse
    }

    fun updateRelayManualThree(token : String, jsonObject: JsonObject){
        relayManualThreeResponse = repository.updateRelayManualThree(token,jsonObject)
    }
    fun getRelayManualThreeResponse(): LiveData<UpdateRelayResponse> {
        return relayManualThreeResponse
    }

    fun updateRelayManualFive(token : String, jsonObject: JsonObject){
        relayManualFiveResponse = repository.updateRelayManualFive(token,jsonObject)
    }
    fun getRelayManualFiveResponse(): LiveData<UpdateRelayResponse> {
        return relayManualFiveResponse
    }

    fun updateRelayManualSix(token : String, jsonObject: JsonObject){
        relayManualSixResponse = repository.updateRelayManualSix(token,jsonObject)
    }
    fun getRelayManualSixResponse(): LiveData<UpdateRelayResponse> {
        return relayManualOneResponse
    }
}