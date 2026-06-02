package com.fullstackteam.androidproject.model

import com.google.gson.annotations.SerializedName

data class AnimeListItem(
    val description: String,
    val episodes: Int,

    // API devuelve array: ["Action", "Drama", ...]
    val genres: List<String>,

    // MongoDB devuelve "_id"
    @SerializedName("_id")
    val id: String,

    val image_url: String,
    val name: String,
    val season: String
)