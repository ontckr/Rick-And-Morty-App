package com.onat.rickandmorty.character

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import com.onat.rickandmorty.R

class CharacterActivity : AppCompatActivity() {

    private val viewModel: CharacterViewModel by lazy {
        ViewModelProvider(this).get(CharacterViewModel::class.java)
    }

    private val epoxyController = CharacterDetailEpoxyController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.characterByIdData.observe(this){response ->
            epoxyController.characterResponse = response

            if (response == null) {
                Toast.makeText(this@CharacterActivity, "Request Error", Toast.LENGTH_SHORT).show()
                return@observe
            }
        }
        viewModel.getCharacter(intent.getIntExtra("id", 1))


        val epoxyRecyclerView = findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerViewHome)
        epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
    }
}