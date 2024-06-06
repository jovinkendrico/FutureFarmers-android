package com.example.futurefarmers.ui.level

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.futurefarmers.data.repository.MainRepository
import com.example.futurefarmers.data.response.GetRelayConfigResponse
import com.example.futurefarmers.data.response.UpdateRelayConfigResponse
import com.google.gson.JsonObject

class LevelViewModel(private var repository: MainRepository): ViewModel() {
    private lateinit var levelResponse: LiveData<GetRelayConfigResponse>
    private lateinit var updateLevelResponse: LiveData<UpdateRelayConfigResponse>

    fun getSession(): LiveData<String> {
        return repository.getSession().asLiveData()
    }

    fun getlecv(token: String){
        levelResponse = repository.getRelayConfig(token)
    }

    fun getLevelResponse(): LiveData<GetRelayConfigResponse> {
        return levelResponse
    }

    fun updateLevel(token: String, jsonObject: JsonObject){
        updateLevelResponse = repository.updateRelayConfig(token, jsonObject)
    }
    fun getUpdateLevelResponse(): LiveData<UpdateRelayConfigResponse> {
        return updateLevelResponse
    }
}