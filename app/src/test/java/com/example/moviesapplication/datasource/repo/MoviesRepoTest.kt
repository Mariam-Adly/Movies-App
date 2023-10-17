package com.example.moviesapplication.datasource.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviesapplication.database.LocalSource
import com.example.moviesapplication.datasource.FakeLocalDataSource
import com.example.moviesapplication.datasource.FakeRemoteDataSource
import com.example.moviesapplication.model.Movie
import com.example.moviesapplication.model.RepoInterface
import com.example.moviesapplication.model.Repository
import com.example.moviesapplication.model.ResultData
import com.example.moviesapplication.network.RemoteResource
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class MoviesRepoTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var repo : RepoInterface
    lateinit var localDataSource: LocalSource
    lateinit var remoteDataSource: RemoteResource


    @Before
    fun setUp() {

        var data1= Movie(false,"",123L,"","","",0.0,"","","",true,0.0,0L,true)
        var data : Response<ResultData> = ResultData() as Response<ResultData>

       // data as Response<ResultData>
        localDataSource= FakeLocalDataSource(listOf(data1)as MutableList<Movie>
        )
        remoteDataSource= FakeRemoteDataSource(data)
        repo = Repository.getInstance(remoteDataSource,localDataSource)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun get_current_movie_return_responseData()= runBlocking{
        //when
        var response= repo.allMovies()
        //then

        MatcherAssert.assertThat(response, Matchers.notNullValue())
    }
    @Test

    fun get_current_movies_return_listOfMovies()= runBlocking{

        //when
        var response= repo.getStoredMovies()
        //then

        MatcherAssert.assertThat(response, Matchers.notNullValue())
    }

}