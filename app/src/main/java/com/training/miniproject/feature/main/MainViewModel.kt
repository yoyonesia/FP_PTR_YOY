package com.training.miniproject.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.training.miniproject.model.cartoon.Cartoon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val cartoonPagingSource: CartoonPagingSource) : ViewModel() {

    fun getCartoons(): Flow<PagingData<Cartoon>> {
        return Pager(
            config = PagingConfig(pageSize = 34, maxSize = 200),
            pagingSourceFactory = {cartoonPagingSource}
        ).flow.cachedIn(viewModelScope)
    }

}