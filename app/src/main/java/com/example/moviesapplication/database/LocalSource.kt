package com.example.moviesapplication.database

import com.example.moviesapplication.model.Movie
import kotlinx.coroutines.flow.Flow


interface LocalSource {

    suspend fun insertMovie(movie: Movie)
    suspend fun getStoredMovies(): Flow<List<Movie>>
    suspend fun deleteMovie(movie: Movie)

}