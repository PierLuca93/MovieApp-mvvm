package it.android.luca.movieapp.datasource

import android.arch.paging.PageKeyedDataSource
import it.android.luca.movieapp.network.MovieService
import it.android.luca.movieapp.repository.Movie

class MovieDataSource(private val service: MovieService): PageKeyedDataSource<Int, Movie>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        service.getTopRated(1).doOnNext{
            list -> callback.onResult(list!!.results, null, 2)
        }.subscribe()
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        service.getTopRated(params.key).doOnNext{
            callback.onResult(it!!.results, params.key + 1)
        }.subscribe()
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        //no need to implement this
    }
}