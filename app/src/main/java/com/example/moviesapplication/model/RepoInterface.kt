package com.example.moviesapplication.model

import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RepoInterface {

    suspend fun allMovies(): Flow<Response<ResultData>>

    suspend fun searchMovies(txtSearch : String): Flow<Response<ResultData>>

    suspend fun insertMovie(movie: Movie)
    suspend fun getStoredMovies(): Flow<List<Movie>>
    suspend fun deleteMovie(movie: Movie)


}
