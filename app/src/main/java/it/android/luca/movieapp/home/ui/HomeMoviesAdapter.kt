package it.android.luca.movieapp.home.ui

import android.arch.paging.PagedListAdapter
import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import it.android.luca.movieapp.R
import it.android.luca.movieapp.detail.ui.DetailActivity
import it.android.luca.movieapp.network.MovieApi.Companion.IMAGE_URL
import it.android.luca.movieapp.repository.Movie


class HomeMoviesAdapter : PagedListAdapter<Movie, HomeMoviesAdapter.MovieHolder>(POST_COMPARATOR) {

//    private var homeList: ArrayList<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_item, parent, false)
        return MovieHolder(view, parent.context)
    }

//    override fun getItemCount(): Int = homeList.size


    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movie: Movie? = getItem(position)
        holder.bindId(movie?.id.toString())
        Glide.with(holder.context)
            .load(IMAGE_URL + movie?.poster_path)
            .into(holder.poster)
    }

//    fun addItems(movies: PagedList<Movie>) {
//        val oldcount = itemCount
////        val diffResult = DiffUtil.calculateDiff(MoviesDiffCallback(homeList, movies))
////        if(!homeList.containsAll(movies)) {
////            homeList.addAll(movies)
//        homeList = movies as ArrayList<Movie>
////        diffResult.dispatchUpdatesTo(this)
////            notifyItemRangeInserted(oldcount, 20)
////        }
//        notifyDataSetChanged()
////        if(oldcount != 0 ) notifyItemRangeInserted(oldcount, 20) else notifyDataSetChanged()
//    }


    class MovieHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView), View.OnClickListener {


        val poster: AppCompatImageView = itemView.findViewById(R.id.poster)
        lateinit var id: String

        init {
            itemView.setOnClickListener(this)
        }

        fun bindId(id: String) {
            this.id = id
        }

        override fun onClick(v: View?) {
            DetailActivity.createIntent(context, id)
        }

    }

    companion object {
        private val PAYLOAD_SCORE = Any()
        val POST_COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun getChangePayload(oldItem: Movie, newItem: Movie): Any? {
                return PAYLOAD_SCORE
            }
        }

    }

}

