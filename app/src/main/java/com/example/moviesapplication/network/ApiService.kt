package com.example.moviesapplication.network

import com.example.moviesapplication.model.ResultData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

   // @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlYzdlMzhjNzkwMmQ1MzgyODliY2M2MTQ3Yzc2NjJkYiIsInN1YiI6IjY1MmMzZmQzZjI4ODM4MDJhMjVlYjk0MSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.b1EFMgxP2bzXkrwdtE5H93WyL47mmbJnc_z3Ez3NzOk", "accept: application/json")
    @GET("movie/popular?api_key=ec7e38c7902d538289bcc6147c7662db&language=en-US&page=1")
    suspend fun allMovies(): Response<ResultData>

}