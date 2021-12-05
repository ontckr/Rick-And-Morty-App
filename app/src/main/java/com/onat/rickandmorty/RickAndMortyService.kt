package com.onat.rickandmorty


import retrofit2.http.GET
import retrofit2.Call

interface RickAndMortyService {

    @GET("character/5")
    fun getCharacterById(): Call<Any>
}