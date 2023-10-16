package com.example.moviesapplication.network

import android.util.Log
import com.example.moviesapplication.model.ResultData
import retrofit2.Response

class MoviesClient private constructor():RemoteResource {

     val apiService : ApiService by lazy {
        RetrofitHelper.getInstance().create(ApiService::class.java)
    }

    companion object {
        private var instance : MoviesClient? = null
        fun getInstance(): MoviesClient {
            return instance?: synchronized(this){
                val temp = MoviesClient()
                instance = temp
                temp
            }
        }
    }

    override suspend fun getMoviesOverNetwork(): Response<ResultData> {
        return apiService.allMovies()
    }
}