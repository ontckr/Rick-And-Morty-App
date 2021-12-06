package com.onat.rickandmorty.network

import com.onat.rickandmorty.network.GetCharacterByIdResponse
import retrofit2.http.GET
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyService {

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") characterId: Int
    ): Response<GetCharacterByIdResponse>

    @GET("character")
    suspend fun getCharacters(
        @Query("page") pageIndex: Int
    ): Response<GetAllCharactersResponse>
}