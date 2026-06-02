package com.fullstackteam.androidproject.model

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fullstackteam.androidproject.databinding.AnimeItemBinding
import com.squareup.picasso.Picasso

// ViewHolder que muestra cada anime en el RecyclerView
class AnimeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val textAnimeName: TextView
    val textAnimeSeason: TextView
    val textAnimeGenres: TextView
    val imageAnime: ImageView

    private val binding = AnimeItemBinding.bind(view)

    init {
        textAnimeName = binding.textName
        textAnimeSeason = binding.textSeason
        textAnimeGenres = binding.textGenres
        imageAnime = binding.imageAnime
    }

    // Renderiza los datos en la vista
    fun render(anime: AnimeListItem, onClickListener: (AnimeListItem) -> Unit) {
        textAnimeName.text = anime.name
        textAnimeSeason.text = anime.season

        // FIX CRÍTICO: genres ahora es List<String>
        textAnimeGenres.text = anime.genres.joinToString(", ")

        Picasso.get()
            .load(anime.image_url)
            .into(imageAnime)

        itemView.setOnClickListener {
            onClickListener(anime)
        }
    }
}