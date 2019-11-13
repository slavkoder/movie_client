package dev.slavko.movieclient.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.slavko.movieclient.R
import dev.slavko.movieclient.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.MovieVH>() {

    private val movies: MutableList<Movie> = mutableListOf()

    fun setData(data: List<Movie>) {
        movies.clear()
        movies.addAll(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {
        val inflater = LayoutInflater.from(parent.context)

        return MovieVH(inflater.inflate(R.layout.item_movie, parent, false))
    }


    override fun onBindViewHolder(holder: MovieVH, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size

    class MovieVH(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: Movie) {
            itemView.movieTitle.text = movie.originalTitle
            itemView.movieSummary.text = movie.overview

            Picasso.get().load(movie.posterUrl).into(itemView.moviePoster)
        }

    }
}