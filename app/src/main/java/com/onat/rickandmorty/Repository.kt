package com.onat.rickandmorty

import com.onat.rickandmorty.network.GetCharacterByIdResponse
import com.onat.rickandmorty.network.NetworkLayer

class Repository {

    suspend fun getCharacterById(id: Int): GetCharacterByIdResponse? {
        val request = NetworkLayer.apiClient.getCharacterById(id)

        if (request.isSuccessful) {
            return request.body()!!
        }
        return null
    }
}