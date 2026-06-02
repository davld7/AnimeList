package com.fullstackteam.androidproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.fullstackteam.androidproject.databinding.ActivityAnimeDetailBinding
import com.squareup.picasso.Picasso

class AnimeDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAnimeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonDetailGoBack.text = "Regresar"

        val name = intent.getStringExtra("name") ?: "Sin nombre"
        val description = intent.getStringExtra("description") ?: "Sin descripción"
        val episodes = intent.getIntExtra("episodes", 0)
        val season = intent.getStringExtra("season") ?: "Desconocida"
        val genres = intent.getStringExtra("genres") ?: "Sin géneros"
        val imageUrl = intent.getStringExtra("image_url")

        binding.textDetailName.text = name
        binding.textDetailDescription.text = "Descripción: $description"
        binding.textDetailEpisodes.text = "Episodio(s): $episodes"
        binding.textDetailSeason.text = "Temporada: $season"
        binding.textDetailGenres.text = "Género(s): $genres"

        // SAFE IMAGE LOAD
        if (!imageUrl.isNullOrEmpty()) {
            Picasso.get()
                .load(imageUrl)
                .into(binding.imageDetailAnime)
        }
    }

    fun goBack(view: View) {
        finish()
    }
}