package com.example.moviesapplication.moviesscreen.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapplication.R
import com.example.moviesapplication.databinding.ActivityMoviesBinding
import com.example.moviesapplication.model.Repository
import com.example.moviesapplication.moviesscreen.ApiState
import com.example.moviesapplication.moviesscreen.viewmodel.MoviesViewModel
import com.example.moviesapplication.moviesscreen.viewmodel.MoviesViewModelFactory
import com.example.moviesapplication.network.MoviesClient
import com.google.gson.Gson

class MoviesActivity : AppCompatActivity() {

    lateinit var moviesViewModel: MoviesViewModel
    lateinit var moviesViewModelFactory: MoviesViewModelFactory
    lateinit var moviesAdapter: MoviesAdapter
    lateinit var binding: ActivityMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        moviesViewModelFactory = MoviesViewModelFactory(
            Repository.getInstance(
                MoviesClient.getInstance()))
        moviesViewModel = ViewModelProvider(this,moviesViewModelFactory).get(MoviesViewModel::class.java)

        moviesViewModel.getMovies()

        moviesAdapter = MoviesAdapter {
           /* val gson = Gson().toJson(it)
            val intent = Intent(this@AthletesActivity, AthletesDetailsActivity::class.java)
            intent.putExtra("myObject",gson)
            startActivity(intent)*/
        }

        binding.rvMovies.apply {
            this.adapter = moviesAdapter
            layoutManager = LinearLayoutManager(context)
        }

        lifecycleScope.launchWhenStarted {
            moviesViewModel.movie.collect{
                when(it){
                    is ApiState.Loading->{
                        binding.rvMovies.isVisible=false
                        binding.progressBar.isVisible = true
                    }
                    is ApiState.Failure -> {
                        binding.rvMovies.isVisible = false
                        binding.progressBar.isVisible = true
                        Log.d("main", "onCreate: ${it.msg}")
                    }
                    is ApiState.Success->{
                        binding.rvMovies.isVisible = true
                        binding.progressBar.isVisible = false
                      //  moviesViewModel.addAthlete(it.data.body()?.athletes)
                        moviesAdapter.submitList(it.data.body()?.results)
                        moviesAdapter.notifyDataSetChanged()
                    }
                    is ApiState.Empty->{

                    }
                    is ApiState.SuccessDB->{

                    }
                }
            }
        }


    }


}