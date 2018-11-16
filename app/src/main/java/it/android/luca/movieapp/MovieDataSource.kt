package it.android.luca.movieapp

import android.arch.paging.PageKeyedDataSource
import it.android.luca.movieapp.network.MovieService
import it.android.luca.movieapp.repository.Movie

class MovieDataSource(val service: MovieService): PageKeyedDataSource<Int, Movie>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        service.getTopRated(1).doOnNext{
            list -> callback.onResult(list!!.results, null, 2)
        }.subscribe()
//        callback.onResult(arrayListOf(Movie(id = 1, title = "primo", poster_path = "/1d8ftx6u52ZszwelP3xfl74x7t6.jpg"), Movie(id = 2, title = "secondo", poster_path = "/1d8ftx6u52ZszwelP3xfl74x7t6.jpg"),Movie(id = 3, title = "primo", poster_path = "/1d8ftx6u52ZszwelP3xfl74x7t6.jpg"), Movie(id = 4, title = "secondo", poster_path = "/1d8ftx6u52ZszwelP3xfl74x7t6.jpg")), null, 2)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        service.getTopRated(params.key).subscribe{
            callback.onResult(it!!.results, params.key + 1)
        }
//        callback.onResult(arrayListOf(Movie(id = 5, title = "primo", poster_path = "/1d8ftx6u52ZszwelP3xfl74x7t6.jpg"), Movie(id = 6, title = "secondo", poster_path = "/1d8ftx6u52ZszwelP3xfl74x7t6.jpg"), Movie(id = 7, title = "primo", poster_path = "/1d8ftx6u52ZszwelP3xfl74x7t6.jpg"), Movie(id = 8, title = "secondo", poster_path = "/1d8ftx6u52ZszwelP3xfl74x7t6.jpg")), 3)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}