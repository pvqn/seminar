package com.example.seminar.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.seminar.MainAdapter
import com.example.seminar.db.ItemDao
import com.example.seminar.pagination.MainPagination

class MainViewModel(
    private val dao: ItemDao
) : ViewModel() {

    val data = Pager(
        PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            initialLoadSize = 20
        )
    ) {
        MainPagination(dao)
    }.flow.cachedIn(viewModelScope)

    companion object {
        fun factory(dao: ItemDao) = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(dao) as T
            }
        }
    }
}