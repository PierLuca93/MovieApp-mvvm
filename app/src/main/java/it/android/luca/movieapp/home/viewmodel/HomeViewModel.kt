package it.android.luca.movieapp.home.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import it.android.luca.movieapp.DataSourceFactory
import it.android.luca.movieapp.repository.Movie
import java.util.concurrent.Executors

class HomeViewModel(val factory: DataSourceFactory): ViewModel() {

    var homeFeed: PublishSubject<Int> = PublishSubject.create()
    var movieList = MutableLiveData<PagedList<Movie>>()
    val homeMoviesList: LiveData<PagedList<Movie>>
    val errorMessage = MutableLiveData<String>()
    val inProgress = MutableLiveData<Boolean>()
//    private val repoResult = map(movieList) {
//        LivePagedListBuilder(factory, 20).setFetchExecutor(Executors.newFixedThreadPool(5)).build()
//    }
//    val posts = switchMap(repoResult) { it }!!
    private val subscription: CompositeDisposable = CompositeDisposable()

    init {
        homeMoviesList = LivePagedListBuilder(factory, 20).setFetchExecutor(Executors.newFixedThreadPool(5)).build()
        homeMoviesList.value
//        subscription
//            .add(homeFeed.subscribe {
//                service.getTopRated(it)
//                    .filter { it != null }
//                    .doFinally {
//                        inProgress.value = false
//                    }
//                    .subscribe(
//                        {
//                            if(!movieList.containsAll(it!!.results)) {
//                                movieList.addAll(it!!.results)
//                                homeMoviesList.value = movieList
//                            }
//                        },
//                        {
//                            errorMessage.value = it.message
//                        })
//            })
    }

//    fun refresh() {
//        repoResult.value?.refresh?.invoke()
//    }
//
//    fun showSubreddit(subreddit: String): Boolean {
////        if (homeMoviesList.value == subreddit) {
////            return false
////        }
////        subredditName.value = subreddit
//        return true
//    }
//
//    fun retry() {
//        val listing = repoResult?.value
//        listing?.retry?.invoke()
//    }
//
//    fun currentSubreddit(): ArrayList<Movie>? = homeMoviesList.value

//    override fun onCleared() {
//        super.onCleared()
//        clear()
//    }
//
//    fun clear(){
//        subscription.clear()
//    }
//
//    fun fetchMovies() {
//        homeFeed.onNext(1)
//    }
//
//    fun loadNextPage(page: Int) {
//        homeFeed.onNext(page)
//    }


}