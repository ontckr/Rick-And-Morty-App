package com.onat.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameTextView = findViewById<TextView>(R.id.name)
        val statusTextView = findViewById<TextView>(R.id.status)
        val speciesTextView = findViewById<TextView>(R.id.species)
        val genderTextView = findViewById<TextView>(R.id.gender)
        val profilePictureImageView = findViewById<ImageView>(R.id.image)


        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val rickAndMortyService: RickAndMortyService = retrofit.create(RickAndMortyService::class.java)

        rickAndMortyService.getCharacterById(5).enqueue(object: Callback<GetCharacterByIdResponse>  {
            override fun onResponse(call: Call<GetCharacterByIdResponse>, response: Response<GetCharacterByIdResponse>) {
                Log.i("Response ", response.toString())

                if (!response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "Error on request", Toast.LENGTH_LONG).show()
                    return
                }

                val responseBody = response.body()!!
                nameTextView.text = responseBody.name
                statusTextView.text = responseBody.status
                speciesTextView.text = responseBody.species
                genderTextView.text = responseBody.gender
                Picasso.get().load(responseBody.image).into(profilePictureImageView)


            }

            override fun onFailure(call: Call<GetCharacterByIdResponse>, t: Throwable) {
                Log.i("Response ", t.message ?: "Error")
            }
        })
    }
}