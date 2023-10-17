package com.example.moviesapplication.moviesscreen

import com.example.moviesapplication.model.Movie
import com.example.moviesapplication.model.ResultData
import retrofit2.Response

sealed class ApiState{
        class Success(val data: Response<ResultData>):ApiState()
        class SuccessDB(val data: List<Movie>) : ApiState()
        class Failure(val msg: Throwable): ApiState()
        object Loading : ApiState()
        object Empty :  ApiState()

}
