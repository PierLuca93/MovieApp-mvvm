package it.android.luca.movieapp.home.presenter

interface HomePresenter {
    fun fetchMovies()
    fun loadNextPage(page: Int)
}