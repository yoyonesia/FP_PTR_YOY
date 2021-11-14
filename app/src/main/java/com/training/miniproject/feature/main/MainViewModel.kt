package com.training.miniproject.feature.main

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.training.miniproject.feature.AppViewModel
import com.training.miniproject.model.cartoon.Cartoon
import com.training.miniproject.repository.LoginRepository
import com.training.miniproject.repository.api.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val cartoonPagingSource: CartoonPagingSource,
    val userRepository: UserRepository,
    val loginRepository: LoginRepository
    ) : AppViewModel() {

    fun getCartoons(): Flow<PagingData<Cartoon>> {
        return Pager(
            config = PagingConfig(pageSize = 34, maxSize = 200),
            pagingSourceFactory = {cartoonPagingSource}
        ).flow.cachedIn(viewModelScope)
    }

    fun logout() = viewModelScope.launch{
        userRepository.deleteUser()
        loginRepository.clearToken()
    }

}