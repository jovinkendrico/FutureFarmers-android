package com.example.futurefarmers.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.futurefarmers.data.preferences.UserPreference
import com.example.futurefarmers.data.remote.config.ApiService
import com.example.futurefarmers.data.response.RecordsItem
import retrofit2.HttpException
import java.io.IOException

private const val TMDB_STARTING_PAGE_INDEX = 1


class RelayHistoryPagingSource(
    private val service: ApiService
) : PagingSource<Int, RecordsItem>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecordsItem> {
        val pageIndex = params.key ?: TMDB_STARTING_PAGE_INDEX
        return try {
            val response = service.getRelayHistory(pageIndex)
            val histories = response.records ?: emptyList()
            val nextKey = if (histories.isEmpty()) {
                null
            } else {
                pageIndex + 1
            }
            LoadResult.Page(
                data = histories,
                prevKey = if (pageIndex == TMDB_STARTING_PAGE_INDEX) null else pageIndex - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    /**
     * The refresh key is used for subsequent calls to PagingSource.Load after the initial load.
     */
    override fun getRefreshKey(state: PagingState<Int, RecordsItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
    companion object {
        const val TAG="MainRepository"
        @Volatile
        private var instance: RelayHistoryPagingSource? = null
        fun getInstance(
            apiService: ApiService,
        ): RelayHistoryPagingSource =
            instance ?: synchronized(this) {
                instance ?: RelayHistoryPagingSource(apiService)
            }.also { instance = it }
    }
}