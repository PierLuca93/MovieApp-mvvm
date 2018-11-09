package it.android.luca.movieapp.detail.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View.GONE
import com.bumptech.glide.Glide.init
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import it.android.luca.movieapp.network.MovieService
import it.android.luca.movieapp.repository.Movie

class DetailViewModel(val service: MovieService): ViewModel() {

    val movieDetail = MutableLiveData<Movie>()
    val errorMessage = MutableLiveData<String>()
    val inProgress = MutableLiveData<Int>()
    val subscription: CompositeDisposable = CompositeDisposable()


    fun clear() {
        subscription.clear()
    }

    fun fetchMovie(id: String){
        subscription
            .add(
                service.getMovie(id)
                    .filter { it != null }
                    .doFinally { inProgress.value = GONE }
                    .subscribe(
                        {
                            movieDetail.value = it
                        },
                        {
                            errorMessage.value = it.message
                        })
            )
    }


}