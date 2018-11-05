package it.android.luca.movieapp.ui

import android.content.Context
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import it.android.luca.movieapp.R
import it.android.luca.movieapp.repository.Movie

class HomeMoviesAdapter : RecyclerView.Adapter<HomeMoviesAdapter.MovieHolder>() {

    private var homeList: List<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_item, parent, false)
        return MovieHolder(view, parent.context)
    }

    override fun getItemCount(): Int = homeList.size


    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        Glide.with(holder.context)
            .load("https://image.tmdb.org/t/p/w185_and_h278_bestv2"+homeList[position].poster_path)
            .into(holder.poster)
    }

    fun setItems(movies: List<Movie>){
        homeList = movies
        notifyDataSetChanged()
    }


    class MovieHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView), View.OnClickListener {


        val poster: AppCompatImageView = itemView.findViewById(R.id.poster)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
        }

    }

}

