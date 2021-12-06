package com.onat.rickandmorty.character

import android.util.Log
import com.airbnb.epoxy.EpoxyController
import com.onat.rickandmorty.R
import com.onat.rickandmorty.databinding.CharacterDetailEpisodesBinding
import com.onat.rickandmorty.databinding.CharacterDetailInformationBinding
import com.onat.rickandmorty.databinding.EpisodeModelBinding
import com.onat.rickandmorty.helpers.LoadingEpoxyModel
import com.onat.rickandmorty.helpers.ViewBindingKotlinModel
import com.onat.rickandmorty.network.GetCharacterByIdResponse
import com.squareup.picasso.Picasso

class CharacterDetailEpoxyController: EpoxyController() {

    private var isLoading: Boolean = true
    set(value) {
        field = value
        if (field) {
            requestModelBuild()
        }
    }

    var characterResponse: GetCharacterByIdResponse? = null
    set(value) {
        field = value
        if (field != null) {
            isLoading = false
            requestModelBuild()
        }
    }

    override fun buildModels() {

        if (isLoading) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        CharacterDetailInformationEpoxyModel(
            name = characterResponse!!.name,
            status = characterResponse!!.status,
            species = characterResponse!!.species,
            gender = characterResponse!!.gender,
            image = characterResponse!!.image
        ).id("info").addTo(this)

        Log.i("character: " , characterResponse.toString())
    }

    data class CharacterDetailInformationEpoxyModel(
        val name: String,
        val status: String,
        val species: String,
        val gender: String,
        val image: String

    ): ViewBindingKotlinModel<CharacterDetailInformationBinding>(R.layout.character_detail_information) {

        override fun CharacterDetailInformationBinding.bind() {
            nameTextView.text = name
            statusTextView.text = status
            speciesTextView.text = species
            genderTextView.text = gender
            Picasso.get().load(image).into(imageView)
        }
    }

    data class CharacterDetailEpisodesEpoxyModel(
        val data: List<Any>
    ): ViewBindingKotlinModel<EpisodeModelBinding>(R.layout.episode_model) {
        override fun EpisodeModelBinding.bind() {
            TODO()
        }
    }
}