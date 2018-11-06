package it.android.luca.movieapp.network

import io.reactivex.Observable
import it.android.luca.movieapp.model.MoviesList
import it.android.luca.movieapp.repository.Movie
import retrofit2.http.GET


interface MovieApi {
    @GET("top_rated")
    fun topRated(): Observable<MoviesList>

    @GET("132")
    fun unaltro(): Observable<Movie>

}