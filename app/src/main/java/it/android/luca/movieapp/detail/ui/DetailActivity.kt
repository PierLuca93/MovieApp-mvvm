package it.android.luca.movieapp.detail.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View.VISIBLE
import com.bumptech.glide.Glide
import it.android.luca.movieapp.App
import it.android.luca.movieapp.BaseActivity
import it.android.luca.movieapp.R
import it.android.luca.movieapp.detail.viewmodel.DetailViewModel
import it.android.luca.movieapp.detail.viewmodel.DetailViewModelFactory
import it.android.luca.movieapp.di.DaggerDetailComponent
import it.android.luca.movieapp.di.DetailModule
import it.android.luca.movieapp.network.MovieApi.Companion.IMAGE_URL
import it.android.luca.movieapp.repository.Movie
import kotlinx.android.synthetic.main.activity_detail.*
import java.text.SimpleDateFormat
import javax.inject.Inject

class DetailActivity : BaseActivity(), DynamicColorsActivity{

    @Inject
    lateinit var factory: DetailViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initDagger()
        initToolbar()
        val id = intent?.extras?.getString(MOVIE_ID)
        val viewModel = ViewModelProviders.of(this, factory).get(DetailViewModel::class.java)
        viewModel.fetchMovie(id!!)
        viewModel.movieDetail.observe(this@DetailActivity, Observer { it?.let { showMovie(it) } })
        viewModel.inProgress.observe(this@DetailActivity, Observer { showLoading(it == VISIBLE) } )
        viewModel.errorMessage.observe(this@DetailActivity, Observer { it?.let{ showError(it) } })
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun initDagger(){
        DaggerDetailComponent.builder()
            .appComponent((application as App).getAppComponent())
            .detailModule(DetailModule())
            .build().inject(this)
    }

    private fun showMovie(item: Movie) {
        collapsing_toolbar.title = item.title
        val date = SimpleDateFormat("yyyy-MM-dd").parse(item.release_date)
        release_date.text = SimpleDateFormat("dd-MM-yyyy").format(date)
        description.text = item.overview
        Glide.with(this)
            .load(IMAGE_URL + item.poster_path)
            .into(MoviePosterTarget(poster, this))
    }

    override fun setTextColor(color: Int){
        toolbar.navigationIcon?.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        collapsing_toolbar.setCollapsedTitleTextColor(color)
    }

    override fun setBackgroundColor(color: Int){
        collapsing_toolbar.setContentScrimColor(color)
    }


    companion object {

        const val MOVIE_ID = "movie_id"

        fun createIntent(context: Context, id: String){
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(MOVIE_ID, id)
            context.startActivity(intent)
        }
    }
}
