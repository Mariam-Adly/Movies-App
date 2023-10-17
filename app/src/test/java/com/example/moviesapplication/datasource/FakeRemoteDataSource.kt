package com.example.moviesapplication.datasource

import com.example.moviesapplication.model.ResultData
import com.example.moviesapplication.network.RemoteResource
import retrofit2.Response

class FakeRemoteDataSource(private var data : Response<ResultData>): RemoteResource {


    override suspend fun getMoviesOverNetwork(): Response<ResultData> {
        return data
    }

    override suspend fun getSearchMoviesOverNetwork(txtSearch: String): Response<ResultData> {
        return data
    }
}