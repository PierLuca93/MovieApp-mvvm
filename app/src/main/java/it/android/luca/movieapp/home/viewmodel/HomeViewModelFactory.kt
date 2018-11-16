package it.android.luca.movieapp.home.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import it.android.luca.movieapp.datasource.DataSourceFactory
import it.android.luca.movieapp.network.MovieService
import javax.inject.Inject

class HomeViewModelFactory @Inject constructor(val service: MovieService): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(DataSourceFactory(service)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}
