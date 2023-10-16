package com.example.moviesapplication.model

import com.example.moviesapplication.network.RemoteResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.Response

class Repository private constructor(var remoteResource: RemoteResource):RepoInterface{

    private val _movie = MutableStateFlow<ResultData>(ResultData())
    var movie = _movie.asStateFlow()
    companion object {
        private var instance : Repository? = null
        fun getInstance(remoteResource: RemoteResource):Repository{
            return instance?: synchronized(this){
                val temp = Repository(remoteResource)
                instance = temp
                temp
            }
        }
    }

  /*  override suspend fun getStoredMovies(): Flow<List<Movie>> {
        return localSource.getStoredAthlete()
    }*/

    override suspend fun allMovies(): Flow<Response<ResultData>> = flow {
        emit(remoteResource.getMoviesOverNetwork())
    }
        .flowOn(Dispatchers.IO)

 /*   override suspend fun insertMovies(movie: List<Movie>) {
        localSource.insertAthlete(athlete)
    }*/


}