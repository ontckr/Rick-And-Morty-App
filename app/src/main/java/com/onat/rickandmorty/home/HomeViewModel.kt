package com.onat.rickandmorty.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.onat.rickandmorty.network.GetCharacterByIdResponse


class HomeViewModel: ViewModel() {

    private val pageList: PagedList.Config = PagedList.Config.Builder()
        .setPageSize(20)
        .setPrefetchDistance(40)
        .build()

    private val repository = HomeRepository()

    private val dataSourceFactory = HomeFactory(viewModelScope, repository)
    val homePagedListLiveData:
            LiveData<PagedList<GetCharacterByIdResponse>> = LivePagedListBuilder(dataSourceFactory, pageList).build()
}