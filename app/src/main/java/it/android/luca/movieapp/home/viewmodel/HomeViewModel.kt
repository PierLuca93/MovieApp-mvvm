package it.android.luca.movieapp.home.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import it.android.luca.movieapp.network.MovieService
import it.android.luca.movieapp.repository.Movie

class HomeViewModel(val service: MovieService): ViewModel() {

    var homeFeed: PublishSubject<Int> = PublishSubject.create()
    var movieList = ArrayList<Movie>()
    val homeMoviesList = MutableLiveData<ArrayList<Movie>>()
    val errorMessage = MutableLiveData<String>()
    val inProgress = MutableLiveData<Boolean>()
    private val subscription: CompositeDisposable = CompositeDisposable()

    init {
        subscription
            .add(homeFeed.subscribe {
                service.getTopRated(it)
                    .filter { it != null }
                    .doFinally {
                        inProgress.value = false
                    }
                    .subscribe(
                        {
                            if(!movieList.containsAll(it!!.results)) {
                                movieList.addAll(it!!.results)
                                homeMoviesList.value = movieList
                            }
                        },
                        {
                            errorMessage.value = it.message
                        })
            })
    }

    override fun onCleared() {
        super.onCleared()
        clear()
    }

    fun clear(){
        subscription.clear()
    }

    fun fetchMovies() {
        homeFeed.onNext(1)
    }

    fun loadNextPage(page: Int) {
        homeFeed.onNext(page)
    }


}