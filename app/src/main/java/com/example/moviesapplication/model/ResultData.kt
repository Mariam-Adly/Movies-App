package com.example.moviesapplication.model

import java.io.Serializable

class ResultData : Serializable {
    val page: Long = 0L
    val results: List<Movie>? = null
    val total_pages: Long = 0L
    val total_results: Long = 0L
}