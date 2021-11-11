package com.training.task2.feature.main

import android.net.Uri
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.training.task2.di.CartoonAPI
import com.training.task2.di.LoginAPI
import com.training.task2.model.Cartoon
import com.training.task2.repository.APIService
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class CartoonPagingSource @Inject constructor(val apiService: APIService) : PagingSource<Int, Cartoon>() {
    override fun getRefreshKey(state: PagingState<Int, Cartoon>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cartoon> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            val response = apiService.retriveCartoon(nextPage.toString())
            var nextPageNumber: Int? = null

            if (response?.info?.next != null){
                val uri = Uri.parse(response.info.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }

            Log.d("PagingSource", "nextPage : $nextPage")
            Log.d("PagingSource", "response : $response")
            Log.d("PagingSource", "nextPageNumber : $nextPageNumber")

            LoadResult.Page(
                data = response.cartoons,
                prevKey = null,
                nextKey = nextPageNumber
                )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }
}