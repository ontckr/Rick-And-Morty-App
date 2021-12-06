package com.onat.rickandmorty.network

class GetAllCharactersResponse (
    val info: Info = Info(),
    val results: List<GetCharacterByIdResponse> = emptyList()
    ){
    data class Info(
        val count: Int = 0,
        val pages: Int = 0,
        val next: String? = null,
        val prev: String? = null
    )
}