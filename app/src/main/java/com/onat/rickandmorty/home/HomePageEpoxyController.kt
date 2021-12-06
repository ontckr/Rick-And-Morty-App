package com.onat.rickandmorty.home

import android.util.Log
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.onat.rickandmorty.R
import com.onat.rickandmorty.databinding.HomeCharacterModelBinding
import com.onat.rickandmorty.helpers.LoadingEpoxyModel
import com.onat.rickandmorty.helpers.ViewBindingKotlinModel
import com.onat.rickandmorty.network.GetCharacterByIdResponse
import com.squareup.picasso.Picasso


class HomePageEpoxyController(
    val selectedCharacter: (Int) -> Unit
): PagedListEpoxyController<GetCharacterByIdResponse>(){

    override fun buildItemModel(
        currentPosition: Int,
        item: GetCharacterByIdResponse?
    ): EpoxyModel<*> {
        return HomeItemEpoxyModel(item!!.name, item.image, item.id, selectedCharacter).id(item.id)
    }

    override fun addModels(models: List<EpoxyModel<*>>) {

        if (models.isEmpty()){
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }
        super.addModels(models)
    }

    data class HomeItemEpoxyModel(
        val name: String,
        val image: String,
        val id: Int,
        val selectedCharacter: (Int) -> Unit

    ): ViewBindingKotlinModel<HomeCharacterModelBinding>(R.layout.home_character_model){
        override fun HomeCharacterModelBinding.bind() {
            Log.i("character: ", name)
            nameTextView.text = name
            Picasso.get().load(image).into(imageView)

            root.setOnClickListener {
                selectedCharacter(id)
            }
        }
    }
}