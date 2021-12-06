package com.onat.rickandmorty.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import com.onat.rickandmorty.character.CharacterActivity
import com.onat.rickandmorty.R

class HomeActivity: AppCompatActivity() {

    private val epoxyController = HomePageEpoxyController(::selectedCharacter)

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        viewModel.homePagedListLiveData.observe(this) { list ->
            epoxyController.submitList(list)
        }

        findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerViewHome).setController(epoxyController)
    }



    private fun selectedCharacter(id: Int){
        val intent = Intent(this, CharacterActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent)
    }
}