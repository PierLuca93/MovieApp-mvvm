package it.android.luca.movieapp.di

import dagger.Provides
import dagger.Module
import it.android.luca.movieapp.detail.viewmodel.DetailViewModelFactory
import it.android.luca.movieapp.network.MovieService


@Module
class DetailModule {

    @Provides
    fun provideViewModelFactory(service: MovieService): DetailViewModelFactory{
        return DetailViewModelFactory(service)
    }
}