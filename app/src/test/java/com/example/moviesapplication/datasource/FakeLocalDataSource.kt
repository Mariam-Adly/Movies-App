package com.example.moviesapplication.datasource

import com.example.moviesapplication.database.LocalSource
import com.example.moviesapplication.model.Movie
import com.example.moviesapplication.model.ResultData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import retrofit2.Response


class FakeLocalDataSource(private var movieList: MutableList<Movie> = mutableListOf()
): LocalSource {


    override suspend fun insertMovie(movie: Movie) {
        movieList.add(movie)
    }

    override suspend fun getStoredMovies(): Flow<List<Movie>> {
       return flowOf(movieList)
    }

    override suspend fun deleteMovie(movie: Movie) {
       movieList.remove(movie)
    }


}