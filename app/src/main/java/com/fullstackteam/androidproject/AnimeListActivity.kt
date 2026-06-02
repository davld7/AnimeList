package com.fullstackteam.androidproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.fullstackteam.androidproject.databinding.ActivityAnimeListBinding
import com.fullstackteam.androidproject.model.AnimeAdapter
import com.fullstackteam.androidproject.model.AnimeDBClient
import com.fullstackteam.androidproject.model.AnimeListItem
import kotlin.concurrent.thread

class AnimeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimeListBinding

    private var size: Int = 0
    private var totalPages: Int = 0
    private var pageNumber: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAnimeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGoBack.text = "Regresar"

        getAnimeList()
    }

    fun goBack(view: View) {
        finish()
    }

    fun update(view: View) {
        getAnimeList()
    }

    fun getSize(view: View) {
        if (size > 0) {
            Toast.makeText(this, "$size Animes en la Base de Datos", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Actualiza la lista primero", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getAnimeList() {
        thread {
            try {

                val animeCall = AnimeDBClient.service.animeList(pageNumber)
                val totalCall = AnimeDBClient.service.totalAnimesPages()

                val responseAnime = animeCall.execute()
                val animeListBody = responseAnime.body()

                val responseTotal = totalCall.execute()
                val totalBody = responseTotal.body()

                if (responseAnime.isSuccessful && animeListBody != null) {

                    runOnUiThread {

                        // SAFE NULL HANDLING
                        size = totalBody?.totalAnimes ?: 0
                        totalPages = totalBody?.totalPages ?: 0

                        binding.recyclerViewAnimeList.apply {
                            setHasFixedSize(true)
                            layoutManager = LinearLayoutManager(this@AnimeListActivity)
                            adapter = AnimeAdapter(animeListBody) { onItemClick(it) }
                        }
                    }

                } else {
                    runOnUiThread {
                        Toast.makeText(
                            this,
                            "Error API: ${responseAnime.code()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(
                        this,
                        "Error de conexión: ${e.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    fun getNext(view: View) {
        if (pageNumber < totalPages) {
            pageNumber++
            getAnimeList()
        }
    }

    fun getPrevious(view: View) {
        if (pageNumber > 1) {
            pageNumber--
            getAnimeList()
        }
    }

    private fun onItemClick(anime: AnimeListItem) {
        val intent = Intent(this, AnimeDetailActivity::class.java).apply {
            putExtra("name", anime.name)
            putExtra("description", anime.description)
            putExtra("episodes", anime.episodes)
            putExtra("season", anime.season)

            // FIX CRÍTICO
            putExtra("genres", anime.genres.joinToString(", "))

            putExtra("image_url", anime.image_url)
        }
        startActivity(intent)
    }
}