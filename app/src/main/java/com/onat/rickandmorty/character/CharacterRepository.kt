package com.onat.rickandmorty.character

import com.onat.rickandmorty.network.GetCharacterByIdResponse
import com.onat.rickandmorty.network.NetworkLayer

class CharacterRepository {

    suspend fun getCharacterById(id: Int): GetCharacterByIdResponse? {
        val request = NetworkLayer.apiClient.getCharacterById(id)

        if (request.isSuccessful) {
            return request.body()!!
        }
        return null
    }
}