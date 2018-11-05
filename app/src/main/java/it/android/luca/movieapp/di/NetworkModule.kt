package it.android.luca.movieapp.di

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import com.google.gson.Gson
import dagger.Provides
import javax.inject.Singleton
import com.google.gson.GsonBuilder
import dagger.Module
import it.android.luca.movieapp.network.MovieService
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


@Module
class NetworkModule() {

    private var BASE_URL = "https://api.themoviedb.org/3/movie/"

    @Singleton
    @Provides
    fun provideGson(): Gson {
        val builder = GsonBuilder()
        return builder.create()
    }

    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }


    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}