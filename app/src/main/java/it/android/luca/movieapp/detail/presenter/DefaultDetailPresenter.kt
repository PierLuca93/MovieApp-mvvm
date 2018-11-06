package it.android.luca.movieapp.detail.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import it.android.luca.movieapp.home.presenter.HomePresenter
import it.android.luca.movieapp.network.MovieService
import it.android.luca.movieapp.repository.Movie

class DefaultDetailPresenter(private val view: View):
    DetailPresenter {

    var homeFeed: BehaviorSubject<Boolean> = BehaviorSubject.create()
    val subscription: CompositeDisposable = CompositeDisposable()

    init {
//        subscription
//            .add(homeFeed
//            .subscribe{
//                service.getTopRated()
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribeOn(Schedulers.io())
//                    .filter{it != null}
//                    .doFinally{ }
//                    .subscribe{
//                        view.showMovies(it.results)
//                    }
//            })
    }

    fun clear(){
//        subscription.clear()
    }

    override fun fetchMovie() {
//        homeFeed.onNext(true)
    }



    interface View{
        fun showMovie(item: Movie)

//        fun showLoading(show: Boolean)
    }
}