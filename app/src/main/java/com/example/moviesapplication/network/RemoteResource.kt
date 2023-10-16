package com.example.moviesapplication.network

import com.example.moviesapplication.model.ResultData
import retrofit2.Response

interface RemoteResource {

    suspend fun getMoviesOverNetwork(): Response<ResultData>

}