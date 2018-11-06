package it.android.luca.movieapp.ui

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
import android.util.DisplayMetrics
import it.android.luca.movieapp.util.Utils.Companion.convertDpToPixel


class HomeMoviesAdapter(val column: Int) : RecyclerView.Adapter<HomeMoviesAdapter.MovieHolder>() {

    private var homeList: List<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_item, parent, false)
        return MovieHolder(view, parent.context, column)
    }

    override fun getItemCount(): Int = homeList.size


    override fun onBindViewHolder(holder: MovieHolder, position: Int) {

        Glide.with(holder.context)
            .load("https://image.tmdb.org/t/p/original"+homeList[position].poster_path)
            .into(holder.poster)
    }

    fun setItems(movies: List<Movie>){
        homeList = movies
        notifyDataSetChanged()
    }


    class MovieHolder(itemView: View, val context: Context, column: Int) : RecyclerView.ViewHolder(itemView), View.OnClickListener {


        val poster: AppCompatImageView = itemView.findViewById(R.id.poster)
        val widtha: Int

        init {
            itemView.setOnClickListener(this)
            val displaymetrics = DisplayMetrics()
            (context as Activity).windowManager.defaultDisplay.getMetrics(displaymetrics)
            widtha = (displaymetrics.widthPixels-convertDpToPixel(8, context)) / column
            itemView.layoutParams.width = widtha
            itemView.layoutParams.height = (widtha*1.5).toInt()

        }

        override fun onClick(p0: View?) {
        }

    }

}

