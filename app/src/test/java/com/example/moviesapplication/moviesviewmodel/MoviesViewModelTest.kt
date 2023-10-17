package com.example.moviesapplication.moviesviewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.moviesapplication.MainCoroutineRule
import com.example.moviesapplication.database.LocalSource
import com.example.moviesapplication.datasource.FakeLocalDataSource
import com.example.moviesapplication.datasource.FakeRemoteDataSource
import com.example.moviesapplication.datasource.FakeTestRepositary
import com.example.moviesapplication.model.Movie
import com.example.moviesapplication.model.Repository
import com.example.moviesapplication.model.ResultData
import com.example.moviesapplication.moviesscreen.ApiState
import com.example.moviesapplication.moviesscreen.viewmodel.MoviesViewModel
import com.example.moviesapplication.network.RemoteResource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class MoviesViewModelTest {
    // Subject under test
    private lateinit var movieViewModel: MoviesViewModel

    // Use a fake repository to be injected into the viewmodel
    private lateinit var repository: FakeTestRepositary

    // Executes each task synchronously using Architecture Components.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var localDataSource: LocalSource
    lateinit var remoteDataSource: RemoteResource
    lateinit var  resultData: MutableList<ResultData>
    lateinit var movieList:MutableList<Movie>

    @Before
    fun setupViewModel() {

        var data1= Movie(false,"",123L,"","","",0.0,"","","",true,0.0,0L,true)
        var data : Response<ResultData> = ResultData() as Response<ResultData>

        // data as Response<ResultData>
        localDataSource= FakeLocalDataSource(listOf(data1)as MutableList<Movie>
        )
        remoteDataSource= FakeRemoteDataSource(data)
        repository = FakeTestRepositary(localDataSource,remoteDataSource
        )
        movieViewModel = MoviesViewModel(repository)
    }
    @Test
    fun get_movie_return_list_movies()= runBlocking{
        //given
        var movieList=movieList
        //when
        movieViewModel.getMovies()
        var result=movieViewModel.movie.value
        when (result) {

            ApiState.Empty -> {}
            is ApiState.Failure -> {}
            ApiState.Loading -> {}
            is ApiState.Success -> {movieList = result.data as MutableList<Movie>}
            is ApiState.SuccessDB ->{}
        }
        //Then
        MatcherAssert.assertThat(movieList, IsNull.notNullValue())
    }
}