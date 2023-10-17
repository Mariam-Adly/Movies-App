package com.example.moviesapplication.model

import com.example.moviesapplication.database.LocalSource
import com.example.moviesapplication.network.RemoteResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.Response

class Repository private constructor(var remoteResource: RemoteResource , var localSource: LocalSource):RepoInterface{

    private val _movie = MutableStateFlow<ResultData>(ResultData())
    var movie = _movie.asStateFlow()
    companion object {
        private var instance : Repository? = null
        fun getInstance(remoteResource: RemoteResource , localSource: LocalSource):Repository{
            return instance?: synchronized(this){
                val temp = Repository(remoteResource,localSource)
                instance = temp
                temp
            }
        }
    }


    override suspend fun allMovies(): Flow<Response<ResultData>> = flow {
        emit(remoteResource.getMoviesOverNetwork())
    }
        .flowOn(Dispatchers.IO)

    override suspend fun searchMovies(txtSearch: String): Flow<Response<ResultData>> = flow {
        emit(remoteResource.getSearchMoviesOverNetwork(txtSearch))
    }
        .flowOn(Dispatchers.IO)

    override suspend fun insertMovie(movie: Movie) {
        localSource.insertMovie(movie)
    }

    override suspend fun getStoredMovies(): Flow<List<Movie>> {
        return localSource.getStoredMovies()
    }

    override suspend fun deleteMovie(movie: Movie) {
        localSource.deleteMovie(movie)
    }


}