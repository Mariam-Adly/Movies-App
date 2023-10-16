package com.example.moviesapplication.model

import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface RepoInterface {

   // suspend fun getStoredMovies(): Flow<List<Movie>>
    suspend fun allMovies(): Flow<Response<ResultData>>
   // suspend fun insertMovies(movie: List<Movie>)

}
