package com.onat.rickandmorty.network

import retrofit2.Response

class ApiClient( private val rickAndMortyService: RickAndMortyService) {

    suspend fun getCharacterById(id: Int): Response<GetCharacterByIdResponse> {
        return rickAndMortyService.getCharacterById(id)
    }

    suspend fun getCharacters(pageIndex: Int): Response<GetAllCharactersResponse> {
        return rickAndMortyService.getCharacters(pageIndex)
    }
}