package it.android.luca.movieapp.datasource

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import it.android.luca.movieapp.network.MovieService
import it.android.luca.movieapp.repository.Movie

class DataSourceFactory(val service: MovieService) : DataSource.Factory<Int, Movie>() {
    val sourceLiveData = MutableLiveData<MovieDataSource>()
    override fun create(): DataSource<Int, Movie> {
        val source = MovieDataSource(service)
        sourceLiveData.postValue(source)
        return source
    }
}