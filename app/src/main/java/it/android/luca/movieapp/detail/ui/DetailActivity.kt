package it.android.luca.movieapp.detail.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.text.TextUtils
import android.view.View
import android.view.View.VISIBLE
import com.bumptech.glide.Glide
import it.android.luca.movieapp.App
import it.android.luca.movieapp.BaseActivity
import it.android.luca.movieapp.R
import it.android.luca.movieapp.R.id.*
import it.android.luca.movieapp.detail.presenter.DefaultDetailPresenter
import it.android.luca.movieapp.detail.viewmodel.DetailViewModel
import it.android.luca.movieapp.detail.viewmodel.DetailViewModelFactory
import it.android.luca.movieapp.di.DaggerDetailComponent
import it.android.luca.movieapp.di.DaggerHomeComponent
import it.android.luca.movieapp.di.DetailModule
import it.android.luca.movieapp.di.HomeModule
import it.android.luca.movieapp.home.presenter.DefaultHomePresenter
import it.android.luca.movieapp.network.MovieApi
import it.android.luca.movieapp.network.MovieApi.Companion.IMAGE_URL
import it.android.luca.movieapp.repository.Movie
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : BaseActivity(), DefaultDetailPresenter.View {

    @Inject
    lateinit var factory: DetailViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initDagger()
        initViews()
        val id = intent?.extras?.getString(MOVIE_ID)
        val viewModel = ViewModelProviders.of(this, factory).get(DetailViewModel::class.java)
        viewModel.fetchMovie(id!!)
        viewModel.movieDetail.observe(this@DetailActivity, Observer { it?.let { showMovie(it) } })
        viewModel.inProgress.observe(this@DetailActivity, Observer { showLoading(it == VISIBLE) } )
        viewModel.errorMessage.observe(this@DetailActivity, Observer { it?.let{ showError(it) } })
    }

    override fun onDestroy() {
        super.onDestroy()
//        presenter.clear()
    }

    private fun initViews(){
        description.setOnClickListener {
            if(description.maxLines == 3) {
                description.maxLines = Int.MAX_VALUE
            } else {
                description.maxLines = 3
            }
        }
    }

    private fun initDagger(){
        DaggerDetailComponent.builder()
            .appComponent((application as App).getAppComponent())
            .detailModule(DetailModule(this))
            .build().inject(this)
    }

    override fun showMovie(item: Movie) {
        page_title.text = item.title
        release_date.text = item.release_date
        description.text = item.overview
        Glide.with(this)
            .load(IMAGE_URL+item.poster_path)
            .into(poster)
    }

    companion object {

        val MOVIE_ID = "movie_id"

        fun createIntent(context: Context, id: String){
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(MOVIE_ID, id)
            context.startActivity(intent)
        }
    }
}
