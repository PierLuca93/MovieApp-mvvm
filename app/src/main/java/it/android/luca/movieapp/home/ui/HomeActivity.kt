package it.android.luca.movieapp.home.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import it.android.luca.movieapp.App
import it.android.luca.movieapp.BaseActivity
import it.android.luca.movieapp.home.presenter.DefaultHomePresenter
import it.android.luca.movieapp.R
import it.android.luca.movieapp.di.*
import it.android.luca.movieapp.repository.Movie
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject





class HomeActivity : BaseActivity(), DefaultHomePresenter.View {


    @Inject
    lateinit var presenter: DefaultHomePresenter
    private var column: Int = 2

    private var adapter: HomeMoviesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initDagger()
        initViews()
        presenter.fetchMovies()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.clear()
    }

    private fun initViews(){
        movie_list.layoutManager = GridLayoutManager(this, column)
        movie_list.setHasFixedSize(true)
        adapter = HomeMoviesAdapter(column)
        movie_list.adapter = adapter
    }

    private fun initDagger(){
        DaggerHomeComponent.builder()
            .appComponent((application as App).getAppComponent())
            .homeModule(HomeModule(this))
            .build().inject(this)
    }

    override fun showMovies(items: List<Movie>) {
        adapter?.setItems(items)
    }

    override fun showLoading(show: Boolean) {
        loading.visibility = if (show) VISIBLE else GONE
    }

}
