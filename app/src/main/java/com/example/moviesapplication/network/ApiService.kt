package com.example.moviesapplication.network

import com.example.moviesapplication.model.Movie
import com.example.moviesapplication.model.ResultData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular?api_key=ec7e38c7902d538289bcc6147c7662db&language=en-US&page=1")
    suspend fun allMovies(): Response<ResultData>

    @GET("search/movie?query=&api_key=ec7e38c7902d538289bcc6147c7662db&include_adult=false&language=en-US&page=1")
    suspend fun searchMovies(@Query("query") txtSearch: String) : Response<ResultData>

}