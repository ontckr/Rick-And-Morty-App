package com.onat.rickandmorty

import retrofit2.Response

class ApiClient( private val rickAndMortyService: RickAndMortyService) {
    suspend fun getCharacterById(id: Int): Response<GetCharacterByIdResponse> {
        return rickAndMortyService.getCharacterById(id)
    }
}