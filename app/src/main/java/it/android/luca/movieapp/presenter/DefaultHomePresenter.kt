package it.android.luca.movieapp.presenter

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import it.android.luca.movieapp.network.MovieService
import it.android.luca.movieapp.repository.Movie

class DefaultHomePresenter(val service: MovieService, val view: View):
    HomePresenter {

//    var homeFeed: BehaviorSubject<Movie> = BehaviorSubject.create()
//    var view: MainPresenterView

    init {
        val mSong = service.allSongs()
        mSong.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .subscribe{
                    it -> Log.e("asd", it.toString())
            }
    }

    override fun fetchMovies() {
        val mSong = service.allSongs()
        mSong.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .subscribe{
                    it -> view.showMovies(it.results)
            }
    }



    public interface View{
        fun showMovies(items: List<Movie>)
    }
}