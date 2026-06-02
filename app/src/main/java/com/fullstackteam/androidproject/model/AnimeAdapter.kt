package com.fullstackteam.androidproject.model

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.fullstackteam.androidproject.R

class AnimeAdapter(
    private val animeDataSet: List<AnimeListItem>,
    private val onClickListener: (AnimeListItem) -> Unit
) : RecyclerView.Adapter<AnimeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.anime_item, parent, false) as LinearLayout

        return AnimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.render(animeDataSet[position], onClickListener)
    }

    override fun getItemCount(): Int = animeDataSet.size
}