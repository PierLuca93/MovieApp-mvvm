package it.android.luca.movieapp.home.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import it.android.luca.movieapp.datasource.DataSourceFactory
import it.android.luca.movieapp.repository.Movie
import java.util.concurrent.Executors

class HomeViewModel(val factory: DataSourceFactory): ViewModel() {

    val homeMoviesList: LiveData<PagedList<Movie>> =
        LivePagedListBuilder(factory, 20).setFetchExecutor(Executors.newFixedThreadPool(5)).build()
    val errorMessage = MutableLiveData<String>()
    val inProgress = MutableLiveData<Boolean>()

}