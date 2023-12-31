package com.example.moviesapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName="movies")
data class Movie(
    val adult: Boolean,
    val backdrop_path: String,
   // val genre_ids: List<Long>,
    @PrimaryKey val id: Long,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Long,
    var isFav : Boolean
) : Serializable
