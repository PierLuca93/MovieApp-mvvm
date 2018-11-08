package it.android.luca.movieapp.di

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import com.google.gson.Gson
import dagger.Provides
import javax.inject.Singleton
import com.google.gson.GsonBuilder
import dagger.Module
import it.android.luca.movieapp.BuildConfig
import it.android.luca.movieapp.network.MovieApi
import it.android.luca.movieapp.network.MovieService
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


@Module
class NetworkModule {

    private val BASE_URL = "https://api.themoviedb.org/3/movie/"

    @Singleton
    @Provides
    fun provideGson(): Gson {
        val builder = GsonBuilder()
        return builder.create()
    }

    @Provides
    fun provideMovieApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

    @Provides
    fun provideMovieService(api: MovieApi): MovieService {
        return MovieService(api)
    }


    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provdeClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", BuildConfig.APIKEY)
                .build()
            val requestBuilder = original.newBuilder()
                .url(url)
            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()
    }
}