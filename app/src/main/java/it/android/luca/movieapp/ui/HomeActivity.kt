package it.android.luca.movieapp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import it.android.luca.movieapp.App
import it.android.luca.movieapp.presenter.DefaultHomePresenter
import it.android.luca.movieapp.R
import it.android.luca.movieapp.di.*
import it.android.luca.movieapp.repository.Movie
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject





class HomeActivity : AppCompatActivity(), DefaultHomePresenter.View {


    @Inject
    lateinit var presenter: DefaultHomePresenter

    private var adapter: HomeMoviesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDagger()
        initViews()
        presenter.fetchMovies()
    }

    fun initViews(){
        movie_list.layoutManager = GridLayoutManager(this, 2)
        movie_list.setHasFixedSize(true)
        adapter = HomeMoviesAdapter()
        movie_list.adapter = adapter
    }

    fun initDagger(){
        DaggerHomeComponent.builder()
            .appComponent((application as App).getAppComponent())
            .homeModule(HomeModule(this))
            .build().inject(this)
    }

    override fun showMovies(items: List<Movie>) {
        adapter?.setItems(items)
    }
}
