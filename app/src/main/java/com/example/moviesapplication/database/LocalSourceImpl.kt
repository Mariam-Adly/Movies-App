package com.example.moviesapplication.database

import android.content.Context
import com.example.moviesapplication.model.Movie
import kotlinx.coroutines.flow.Flow

class LocalSourceImpl(context: Context) : LocalSource {

    private val moviesDao : MovieDao by lazy {
        val db  = MoviesDataBase.getInstance(context)
        db.getMovieDao()
    }

    override suspend fun insertMovie(movie: Movie) {
        moviesDao.insertMovie(movie)
        movie.isFav = true
    }

    override suspend fun getStoredMovies(): Flow<List<Movie>> {
        return moviesDao.getAll()
    }

    override suspend fun deleteMovie(movie: Movie) {
        moviesDao.deleteMovie(movie)
        movie.isFav = false
    }


}