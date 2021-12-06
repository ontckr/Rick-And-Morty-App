package com.onat.rickandmorty.home

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.onat.rickandmorty.network.GetCharacterByIdResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class HomeDataSource(
    private val coroutineScope: CoroutineScope,
    private val repository: HomeRepository
): PageKeyedDataSource<Int, GetCharacterByIdResponse>() {


    private fun getPageIndex(next: String?): Int? {
        return next?.split("?page=")?.get(1)?.toInt()
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, GetCharacterByIdResponse>
    ) {
        coroutineScope.launch {
            val page = repository.getCharacterList(1)
            if (page == null){
                callback.onResult(emptyList(), null, null)
                return@launch
            }
            callback.onResult(page.results, null, getPageIndex(page.info.next))
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, GetCharacterByIdResponse>
    ) {}

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, GetCharacterByIdResponse>
    ) {
        coroutineScope.launch {
            val page = repository.getCharacterList(params.key)
            if (page == null){
                callback.onResult(emptyList(), null)
                return@launch
            }
            callback.onResult(page.results, getPageIndex(page.info.next))
        }
    }
}