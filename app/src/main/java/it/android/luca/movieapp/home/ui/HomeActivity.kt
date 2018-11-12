package it.android.luca.movieapp.home.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import it.android.luca.movieapp.App
import it.android.luca.movieapp.BaseActivity
import it.android.luca.movieapp.R
import it.android.luca.movieapp.di.DaggerHomeComponent
import it.android.luca.movieapp.di.HomeModule
import it.android.luca.movieapp.home.viewmodel.HomeViewModel
import it.android.luca.movieapp.home.viewmodel.HomeViewModelFactory
import it.android.luca.movieapp.repository.Movie
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject





class HomeActivity : BaseActivity(){


    @Inject
    lateinit var viewModelFactory: HomeViewModelFactory
    private var column: Int = 2
    private var adapter: HomeMoviesAdapter? = null
    private var state: LinearLayoutManager.SavedState? = null

    private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initDagger()
        initViews()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        viewModel.homeMoviesList.observe(this@HomeActivity, Observer { it?.let { showMovies(it) } })
        viewModel.errorMessage.observe(this@HomeActivity, Observer { it?.let { showError(it) } })
        viewModel.inProgress.observe(this@HomeActivity, Observer { it?.let { showLoading(it) } })
        viewModel.fetchMovies()

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable("position", movie_list.layoutManager?.onSaveInstanceState())
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        state = savedInstanceState?.getParcelable("position")
    }


    private fun initViews(){
        movie_list.layoutManager = GridLayoutManager(this, column)
        movie_list.setHasFixedSize(true)
        adapter = HomeMoviesAdapter()
        movie_list.adapter = adapter
        movie_list.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if((movie_list.layoutManager!! as GridLayoutManager).findLastCompletelyVisibleItemPosition() >= adapter!!.itemCount-5){
                    viewModel.loadNextPage(adapter!!.itemCount/20+1)
                }
            }
        })
    }

    private fun initDagger(){
        DaggerHomeComponent.builder()
            .appComponent((application as App).getAppComponent())
            .homeModule(HomeModule())
            .build().inject(this)
    }

    private fun showMovies(items: List<Movie>) {
        adapter?.addItems(items)
//        if(state != null) {
//            movie_list.layoutManager?.onRestoreInstanceState(state)
//        }
    }

}
