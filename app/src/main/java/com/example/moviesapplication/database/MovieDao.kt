package com.example.moviesapplication.database

import androidx.room.*
import com.example.moviesapplication.model.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("Select * From movies")
    fun getAll(): Flow<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movie: Movie)
    @Delete
    suspend fun deleteMovie(movie: Movie)
}