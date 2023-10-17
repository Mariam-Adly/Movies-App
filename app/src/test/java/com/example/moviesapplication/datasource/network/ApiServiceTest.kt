package com.example.moviesapplication.datasource.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.moviesapplication.network.ApiService
import com.example.moviesapplication.network.RetrofitHelper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ApiServiceTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var apiService: ApiService

    @Before
    fun setUp() {
      apiService = RetrofitHelper.getInstance().create(ApiService::class.java)
    }

    @Test
    fun getMovie_requestKey_Authorized() = runBlocking{
        //Given
        val adult : Boolean = false
        //When
        val response= apiService.allMovies()
        //Then
        MatcherAssert.assertThat(response.body()?.results!![0]?.adult, Matchers.`is` (adult))
        MatcherAssert.assertThat(response.body()?.results?.size, Matchers.notNullValue())


    }

    @After
    fun tearDown() {
    }

    @Test
    fun getCurrentTempData() {
    }

    @Test
    fun getFavWeatherData() {
    }
}