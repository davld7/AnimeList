package com.fullstackteam.androidproject.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// Interfaz que define las llamadas a la API de animes
interface AnimeDBService {

    // Obtiene una página de animes (10 por página)
    @GET("/animes/page")
    fun animeList(
        @Query("page") page: Int = 1
    ): Call<AnimeList>

    // Obtiene el total de animes y páginas disponibles
    @GET("/animes/pages")
    fun totalAnimesPages(): Call<TotalAnimesPages>
}