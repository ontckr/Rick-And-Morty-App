package com.onat.rickandmorty.home

import com.onat.rickandmorty.network.GetAllCharactersResponse
import com.onat.rickandmorty.network.NetworkLayer

class HomeRepository {

    suspend fun getCharacterList(pageIndex: Int): GetAllCharactersResponse?{

        val request = NetworkLayer.apiClient.getCharacters(pageIndex)

        if (request.isSuccessful) {
            return request.body()
        }

        return null
    }
}