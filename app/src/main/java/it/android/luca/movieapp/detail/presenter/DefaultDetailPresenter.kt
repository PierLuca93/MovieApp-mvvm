package it.android.luca.movieapp.detail.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import it.android.luca.movieapp.BasePresenterView
import it.android.luca.movieapp.home.presenter.HomePresenter
import it.android.luca.movieapp.network.MovieService
import it.android.luca.movieapp.repository.Movie

class DefaultDetailPresenter(private val view: View, private val service: MovieService):
    DetailPresenter {

    var movieDetailSubject: BehaviorSubject<String> = BehaviorSubject.create()
    val subscription: CompositeDisposable = CompositeDisposable()

    init {
        subscription
            .add(movieDetailSubject
            .subscribe{
                service.getMovie(it)
                    .filter{it != null}
                    .doFinally{ view.showLoading(false) }
                    .subscribe{
                        view.showMovie(it)
                    }
            })
    }

    fun clear(){
        subscription.clear()
    }

    override fun fetchMovie(id: String) {
        movieDetailSubject.onNext(id)
    }



    interface View: BasePresenterView{
        fun showMovie(item: Movie)
    }
}