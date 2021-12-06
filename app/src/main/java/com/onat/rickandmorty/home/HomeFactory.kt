package com.onat.rickandmorty.home

import androidx.paging.DataSource
import com.onat.rickandmorty.network.GetCharacterByIdResponse
import kotlinx.coroutines.CoroutineScope

class HomeFactory(
    private val coroutineScope: CoroutineScope,
    private val repository: HomeRepository
): DataSource.Factory<Int, GetCharacterByIdResponse>() {

    override fun create(): DataSource<Int, GetCharacterByIdResponse> {
        return HomeDataSource(coroutineScope, repository)
    }
}