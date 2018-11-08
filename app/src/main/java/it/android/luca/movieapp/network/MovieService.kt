package it.android.luca.movieapp.network

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import it.android.luca.movieapp.model.MoviesList
import it.android.luca.movieapp.repository.Movie

class MovieService(private val api: MovieApi) {

    fun getTopRated(): Observable<MoviesList?> =
        api.topRated()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

    fun getMovie(id: String): Observable<Movie> =
        api.movie(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}