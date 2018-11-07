package it.android.luca.movieapp.home.ui

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import it.android.luca.movieapp.R
import it.android.luca.movieapp.repository.Movie
import android.app.Activity
import android.content.Intent
import android.util.DisplayMetrics
import it.android.luca.movieapp.detail.ui.DetailActivity
import it.android.luca.movieapp.util.Utils.Companion.convertDpToPixel


class HomeMoviesAdapter(val column: Int) : RecyclerView.Adapter<HomeMoviesAdapter.MovieHolder>() {

    private var homeList: List<Movie> = ArrayList()
    private val IMAGE_URL = "https://image.tmdb.org/t/p/original"

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_item, parent, false)
        return MovieHolder(view, parent.context, column)
    }

    override fun getItemCount(): Int = homeList.size


    override fun onBindViewHolder(holder: MovieHolder, position: Int) {

        Glide.with(holder.context)
            .load(IMAGE_URL+homeList[position].poster_path)
            .into(holder.poster)
    }

    fun setItems(movies: List<Movie>){
        homeList = movies
        notifyDataSetChanged()
    }


    class MovieHolder(itemView: View, val context: Context, column: Int) : RecyclerView.ViewHolder(itemView), View.OnClickListener {


        val poster: AppCompatImageView = itemView.findViewById(R.id.poster)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val intent = Intent(context, DetailActivity::class.java)
            context.startActivity(intent)
        }

    }

}

