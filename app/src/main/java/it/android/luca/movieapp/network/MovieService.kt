package it.android.luca.movieapp.network

import io.reactivex.Observable
import it.android.luca.movieapp.repository.Movie
import retrofit2.http.GET


interface MovieService {
    @GET("top_rated?api_key=3134412295e446d1fd6113dda83e1cef")
    fun allSongs(): Observable<MoviesResponse>

    @GET("132?api_key=3134412295e446d1fd6113dda83e1cef")
    fun unaltro(): Observable<Movie>

}