package com.example.moviesapplication.moviesscreen.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapplication.database.LocalSourceImpl
import com.example.moviesapplication.databinding.ActivityMoviesBinding
import com.example.moviesapplication.detailsscreen.view.MovieDetailsActivity
import com.example.moviesapplication.model.Repository
import com.example.moviesapplication.moviesscreen.ApiState
import com.example.moviesapplication.moviesscreen.viewmodel.MoviesViewModel
import com.example.moviesapplication.moviesscreen.viewmodel.MoviesViewModelFactory
import com.example.moviesapplication.network.MoviesClient
import com.google.gson.Gson
import java.util.*

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
                MoviesClient.getInstance(),LocalSourceImpl(this)))
        moviesViewModel = ViewModelProvider(this,moviesViewModelFactory).get(MoviesViewModel::class.java)

        moviesViewModel.getMovies()
        moviesViewModel.getStoreMovies()
        moviesAdapter = MoviesAdapter ({
            val gson = Gson().toJson(it)
            val intent = Intent(this@MoviesActivity, MovieDetailsActivity::class.java)
            intent.putExtra("myObject",gson)
            startActivity(intent)
            binding.searchView.setQuery("",false)
            binding.searchView.clearFocus()
        },{
            it.isFav = false
            if(!it.isFav) {
                moviesViewModel.addMovie(it)
            }else{
                moviesViewModel.deleteMovie(it)
            }
        })

        binding.searchView.clearFocus()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               val txtSearch = newText!!.toLowerCase(Locale.getDefault())
                if (txtSearch.isNotEmpty()){
                     moviesViewModel.getSearchMovies(txtSearch)
                }else{
                    moviesViewModel.getMovies()
                }
                return false
            }

        })
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
                    }
                    is ApiState.Success->{
                        binding.rvMovies.isVisible = true
                        binding.progressBar.isVisible = false
                        moviesAdapter.submitList(it.data.body()?.results)
                    }
                    is ApiState.Empty->{

                    }
                    is ApiState.SuccessDB->{
                        binding.rvMovies.isVisible = true
                        binding.progressBar.isVisible = false
                        moviesAdapter.submitList(it.data)
                    }
                }
            }
        }


    }


}