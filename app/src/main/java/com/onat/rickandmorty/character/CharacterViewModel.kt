package com.onat.rickandmorty.character

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onat.rickandmorty.character.CharacterRepository
import com.onat.rickandmorty.network.GetCharacterByIdResponse
import kotlinx.coroutines.launch

class CharacterViewModel: ViewModel() {

    private val repository = CharacterRepository()

    private val _characterByIdData = MutableLiveData<GetCharacterByIdResponse>()
    val characterByIdData: LiveData<GetCharacterByIdResponse> = _characterByIdData

    fun getCharacter(id: Int) {
        viewModelScope.launch {
            val response = repository.getCharacterById(id)

            _characterByIdData.postValue(response)
        }
    }
}