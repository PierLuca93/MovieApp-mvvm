package it.android.luca.movieapp.model

import it.android.luca.movieapp.repository.Movie

data class MoviesResponse(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<Movie>)