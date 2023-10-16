package com.example.moviesapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviesapplication.model.Movie

@Database(entities = arrayOf(Movie::class), version = 1 )
abstract class MoviesDataBase : RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
    companion object{
        @Volatile
        private var INSTANCE: MoviesDataBase? = null
        fun getInstance (ctx: Context): MoviesDataBase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    ctx.applicationContext, MoviesDataBase::class.java, "movies_database")
                    .build()
                INSTANCE = instance
// return instance
                instance }
        }
    }
}
