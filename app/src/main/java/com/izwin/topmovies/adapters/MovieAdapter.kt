package com.izwin.topmovies.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.izwin.topmovies.R
import com.izwin.topmovies.model.MovieModel
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(val context: Context, var movies: ArrayList<MovieModel>, val clickListener: (MovieModel) -> Unit) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies?.get(position)

        holder.itemView.setOnClickListener { clickListener?.invoke(movie) }
        holder.date.text = movie?.date
        holder.rating.text = movie?.rating
        holder.headline.text = movie?.headline

        Glide.with(context)
            .load(movie.multimedia.src)
            .into(holder.photo)


    }

    override fun getItemCount(): Int {
        return movies?.size ?: 0
    }

    fun addMovies(list: ArrayList<MovieModel>) {
        movies.addAll(list)
        notifyDataSetChanged()
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val photo = itemView.photo
        val date = itemView.date
        val rating = itemView.rating
        val headline = itemView.headline
    }

}