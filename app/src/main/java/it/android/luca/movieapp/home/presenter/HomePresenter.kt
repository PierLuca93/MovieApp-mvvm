package it.android.luca.movieapp.home.presenter

interface HomePresenter {
    fun fetchMovies(page: Int)
    fun loadNextPage(page: Int)
}