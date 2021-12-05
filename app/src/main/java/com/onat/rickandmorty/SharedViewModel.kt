package com.onat.rickandmorty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel() {

    private val repository = Repository()

    private val _characterByIdData = MutableLiveData<GetCharacterByIdResponse>()
    val characterByIdData: LiveData<GetCharacterByIdResponse> = _characterByIdData

    fun getCharacter(id: Int) {
        viewModelScope.launch {
            val response = repository.getCharacterById(id)

            _characterByIdData.postValue(response)
        }
    }
}