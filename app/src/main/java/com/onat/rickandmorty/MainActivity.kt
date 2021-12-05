package com.onat.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

    private val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameTextView = findViewById<TextView>(R.id.name)
        val statusTextView = findViewById<TextView>(R.id.status)
        val speciesTextView = findViewById<TextView>(R.id.species)
        val genderTextView = findViewById<TextView>(R.id.gender)
        val profilePictureImageView = findViewById<ImageView>(R.id.image)

        viewModel.getCharacter(3)
        viewModel.characterByIdData.observe(this){response ->

            if (response == null) {
                Toast.makeText(this@MainActivity, "Error on request", Toast.LENGTH_SHORT).show()
                return@observe
            }

            nameTextView.text = response.name
            statusTextView.text = response.status
            speciesTextView.text = response.species
            genderTextView.text = response.gender
            Picasso.get().load(response.image).into(profilePictureImageView)
        }
    }
}