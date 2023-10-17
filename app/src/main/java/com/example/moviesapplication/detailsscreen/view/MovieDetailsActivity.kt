package com.example.moviesapplication.detailsscreen.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.moviesapplication.databinding.ActivityMovieDetailsBinding
import com.example.moviesapplication.model.Movie
import com.google.gson.Gson

class MovieDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityMovieDetailsBinding
    lateinit var movie : Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)






        val movieObj = intent?.getStringExtra("myObject")
        if (movieObj != null) {
            movie = Gson().fromJson(movieObj, Movie::class.java)
            fillViews(movie)
        }



    }

    fun fillViews(movie: Movie) {
        binding.tvName.text = movie.title
        binding.tvDesc.text = movie.overview
        Glide.with(this).load("https://www.themoviedb.org/t/p/w220_and_h330_face${movie.poster_path}").into(binding.imgMovie)


    }
}