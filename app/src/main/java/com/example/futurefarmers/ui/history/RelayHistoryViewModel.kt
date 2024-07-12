package com.example.futurefarmers.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.futurefarmers.data.remote.config.ApiService
import com.example.futurefarmers.data.repository.RelayHistoryPagingSource
import com.example.futurefarmers.data.response.RecordsItem
import kotlinx.coroutines.flow.Flow

private const val NETWORK_PAGE_SIZE = 20
class RelayHistoryViewModel(private val service: ApiService) : ViewModel() {
    val relayHistory: Flow<PagingData<RecordsItem>> = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { RelayHistoryPagingSource(service) }
    ).flow.cachedIn(viewModelScope)
}