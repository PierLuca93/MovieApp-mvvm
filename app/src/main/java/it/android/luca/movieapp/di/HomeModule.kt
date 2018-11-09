package it.android.luca.movieapp.di

import dagger.Provides
import dagger.Module
import it.android.luca.movieapp.home.presenter.DefaultHomePresenter
import it.android.luca.movieapp.home.viewmodel.HomeViewModelFactory
import it.android.luca.movieapp.network.MovieService
import javax.inject.Singleton


@Module
class HomeModule {

    @Provides
    fun provideViewModel(service: MovieService): HomeViewModelFactory{
        return HomeViewModelFactory(service)
    }
}