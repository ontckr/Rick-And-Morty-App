package com.onat.rickandmorty.network

import com.onat.rickandmorty.Location
import com.onat.rickandmorty.Origin

data class GetCharacterByIdResponse(
    val created: String,
    val episode: List<Any>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)