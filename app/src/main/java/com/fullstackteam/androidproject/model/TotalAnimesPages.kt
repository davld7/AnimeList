package com.fullstackteam.androidproject.model

import com.google.gson.annotations.SerializedName

data class TotalAnimesPages(
    @SerializedName("total_animes")
    val totalAnimes: Int,

    @SerializedName("total_pages")
    val totalPages: Int
)