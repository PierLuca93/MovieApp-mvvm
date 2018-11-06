package it.android.luca.movieapp.detail.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import it.android.luca.movieapp.R
import it.android.luca.movieapp.detail.presenter.DefaultDetailPresenter
import it.android.luca.movieapp.repository.Movie
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_home.*

class DetailActivity : AppCompatActivity(), DefaultDetailPresenter.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

    override fun showMovie(item: Movie) {
        page_title.text = item.title
        release_date.text = item.release_date
    }

//    override fun showLoading(show: Boolean) {
//        loading.visibility = if (show) View.VISIBLE else View.GONE
//    }
}
