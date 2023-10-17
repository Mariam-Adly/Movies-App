package com.example.moviesapplication.datasource

import com.example.moviesapplication.database.LocalSource
import com.example.moviesapplication.model.Movie
import com.example.moviesapplication.model.RepoInterface
import com.example.moviesapplication.model.ResultData
import com.example.moviesapplication.network.RemoteResource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class FakeTestRepositary(private var localDataSource: LocalSource,private var remoteDataSource: RemoteResource): RepoInterface {



    override suspend fun allMovies(): Flow<Response<ResultData>> {
        TODO("Not yet implemented")
    }

    override suspend fun searchMovies(txtSearch: String): Flow<Response<ResultData>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertMovie(movie: Movie) {
       localDataSource.insertMovie(movie)
    }

    override suspend fun getStoredMovies(): Flow<List<Movie>> {
       return localDataSource.getStoredMovies()
    }

    override suspend fun deleteMovie(movie: Movie) {
        localDataSource.deleteMovie(movie)
    }
}