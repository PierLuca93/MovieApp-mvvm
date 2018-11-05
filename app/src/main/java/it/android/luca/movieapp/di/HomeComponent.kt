package it.android.luca.movieapp.di

import dagger.Component
import it.android.luca.movieapp.ui.HomeActivity

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class),modules = arrayOf(HomeModule::class))
interface HomeComponent {

    fun inject(activity: HomeActivity)
}