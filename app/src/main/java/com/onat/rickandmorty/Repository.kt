package com.onat.rickandmorty

class Repository {

    suspend fun getCharacterById(id: Int): GetCharacterByIdResponse? {
        val request = NetworkLayer.apiClient.getCharacterById(id)

        if (request.isSuccessful) {
            return request.body()!!
        }
        return null
    }
}