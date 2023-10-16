package com.example.moviesapplication.moviesscreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapplication.model.Movie
import com.example.moviesapplication.model.RepoInterface
import com.example.moviesapplication.moviesscreen.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MoviesViewModel (private val _repo : RepoInterface): ViewModel() {

    private val _movie : MutableStateFlow<ApiState> = MutableStateFlow(ApiState.Empty)
    val movie : StateFlow<ApiState> = _movie

    fun getMovies() = viewModelScope.launch{
        _movie.value = ApiState.Loading
        _repo.allMovies()
            .catch {
                    e-> _movie.value = ApiState.Failure(e)
            }
            .collect{
                    data ->
                _movie.value = ApiState.Success(data)
            }
    }

    fun getSearchMovies(txtSearch : String) = viewModelScope.launch {
        _movie.value = ApiState.Loading
        _repo.searchMovies(txtSearch)
            .catch {
                e -> _movie.value = ApiState.Failure(e)
            }
            .collect{
                data ->
                _movie.value = ApiState.Success(data)
            }
    }


    fun addMovie(movie: Movie?){
        viewModelScope.launch(Dispatchers.IO) {
            if (movie != null) {
                _repo.insertMovie(movie)
                println("item added")
            }
        }

    }

    fun getStoreMovies() =
        viewModelScope.launch{
            _movie.value = ApiState.Loading
            _repo.getStoredMovies()
                .catch {
                        e-> _movie.value = ApiState.Failure(e)
                }
                .collect{
                        data ->
                    _movie.value = ApiState.SuccessDB(data)
                }
        }

}