package com.example.moviesapplication.moviesscreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesapplication.model.RepoInterface

class MoviesViewModelFactory (private val repo : RepoInterface) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(MoviesViewModel::class.java)){
            MoviesViewModel(repo) as T
        }else{
            throw IllegalArgumentException("view model class not found")
        }
    }
}