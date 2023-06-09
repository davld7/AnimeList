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
        // Inflamos el layout de la actividad y asignamos los valores de las vistas correspondientes
        binding = ActivityAnimeDetailBinding.inflate(layoutInflater).apply{
            setContentView(root)
            buttonDetailGoBack.text = "Regresar"
            val name = intent.getStringExtra("name")
            val description = intent.getStringExtra("description")
            val episodes = intent.getIntExtra("episodes",0)
            val season = intent.getStringExtra("season")
            val genres = intent.getStringExtra("genres")
            val image_url = intent.getStringExtra("image_url")
            textDetailName.text = name
            textDetailDescription.text = "Descripción: $description"
            textDetailEpisodes.text = "Episodio(s): $episodes"
            textDetailSeason.text = "Temporada: $season"
            textDetailGenres.text = "Género(s): $genres"
            Picasso.get().load(image_url).into(imageDetailAnime)
        }
    }
    
    // Método para regresar a la actividad anterior
    fun goBack(view: View){
        finish()
    }
}
