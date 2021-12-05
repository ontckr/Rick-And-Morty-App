package com.onat.rickandmorty


import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path

interface RickAndMortyService {

    @GET("character/{id}")
    fun getCharacterById(
        @Path("id") characterId: Int
    ): Call<GetCharacterByIdResponse>
}