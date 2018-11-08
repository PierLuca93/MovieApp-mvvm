package it.android.luca.movieapp

interface BasePresenterView {
    fun showError(error: String)

    fun showLoading(show: Boolean)
}