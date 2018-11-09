package it.android.luca.movieapp.home.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.view.View.GONE
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import it.android.luca.movieapp.network.MovieService
import it.android.luca.movieapp.repository.Movie

class HomeViewModel(val service: MovieService): ViewModel() {

    var homeFeed: BehaviorSubject<Int> = BehaviorSubject.create()
    val homeMoviesList = MutableLiveData<ArrayList<Movie>>()
    val errorMessage = MutableLiveData<String>()
    val inProgress = MutableLiveData<Int>()
    private val subscription: CompositeDisposable = CompositeDisposable()

    init {
        subscription
            .add(homeFeed.subscribe {
                service.getTopRated(it)
                    .filter { it != null }
                    .doFinally {
                        inProgress.value = GONE
                    }
                    .subscribe(
                        {
                            homeMoviesList.value = it!!.results
                        },
                        {
                            errorMessage.value = it.message
                        })
            })
    }

    fun fetchMovies() {
        homeFeed.onNext(1)
    }

    fun loadNextPage(page: Int) {
        homeFeed.onNext(page)
    }


}