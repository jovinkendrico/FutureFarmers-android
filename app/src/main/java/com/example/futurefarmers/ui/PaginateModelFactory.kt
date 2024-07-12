package com.example.futurefarmers.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.futurefarmers.data.remote.config.ApiService
import com.example.futurefarmers.`object`.Injection
import com.example.futurefarmers.ui.history.RelayHistoryViewModel
import com.example.futurefarmers.ui.login.LoginViewModel
import com.example.futurefarmers.ui.register.RegisterViewModel

class PaginateModelFactory(private val service: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RelayHistoryViewModel::class.java) -> {
                RelayHistoryViewModel(service) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
    companion object {
        @Volatile
        private var INSTANCE: PaginateModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): PaginateModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = PaginateModelFactory(Injection.provideApiService())
                }
            }
            return INSTANCE as PaginateModelFactory
        }
    }
}