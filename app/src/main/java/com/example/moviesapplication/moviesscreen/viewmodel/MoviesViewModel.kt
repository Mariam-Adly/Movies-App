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

   /* fun addMovie(athlete: List<Movie>?){
        viewModelScope.launch(Dispatchers.IO) {
            if (athlete != null) {
                _repo.insertAthlete(athlete)
            }
        }

    }*/

   /* fun getStoreAthletes() =
        viewModelScope.launch{
            _athlete.value = ApiState.Loading
            _repo.getStoredAthletes()
                .catch {
                        e-> _athlete.value = ApiState.Failure(e)
                }
                .collect{
                        data ->
                    _athlete.value = ApiState.SuccessDB(data)
                }
        }*/

}