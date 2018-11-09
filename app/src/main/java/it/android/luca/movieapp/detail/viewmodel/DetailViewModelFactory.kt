package it.android.luca.movieapp.detail.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import it.android.luca.movieapp.network.MovieService
import javax.inject.Inject

class DetailViewModelFactory @Inject constructor(val service: MovieService): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(service) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}
