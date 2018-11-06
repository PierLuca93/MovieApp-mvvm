package it.android.luca.movieapp.network

import io.reactivex.Observable
import it.android.luca.movieapp.repository.Movie
import retrofit2.http.GET


interface MovieService {
    @GET("top_rated")
    fun topRated(): Observable<MoviesResponse>

    @GET("132")
    fun unaltro(): Observable<Movie>

}