package it.android.luca.movieapp.network

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import it.android.luca.movieapp.model.MoviesResponse

class MovieService(private val api: MovieApi) {

    fun getTopRated(): Observable<MoviesResponse> =
        api.topRated()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
}