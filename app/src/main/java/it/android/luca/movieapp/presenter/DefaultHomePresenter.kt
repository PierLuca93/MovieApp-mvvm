package it.android.luca.movieapp.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import it.android.luca.movieapp.network.MovieService
import it.android.luca.movieapp.repository.Movie

class DefaultHomePresenter(val service: MovieService, val view: View):
    HomePresenter {

//    var homeFeed: BehaviorSubject<Movie> = BehaviorSubject.create()

    init {
    }

    override fun fetchMovies() {
        val movies = service.topRated()
        movies.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .filter{list -> list != null}
            .doFinally{ view.showLoading(false) }
            .subscribe{
                    it -> view.showMovies(it.results)
            }
    }



    public interface View{
        fun showMovies(items: List<Movie>)

        fun showLoading(show: Boolean)
    }
}