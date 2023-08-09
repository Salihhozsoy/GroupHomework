package com.example.grouphomework

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.grouphomework.databinding.MovieListItemBinding

class MovieListAdapter(private val context: Context, private var movies: List<Movie>) :
    RecyclerView.Adapter<MovieListAdapter.CustomViewHolder>() {

    class CustomViewHolder(binding: MovieListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivMovie = binding.ivMovie

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieListAdapter.CustomViewHolder {
        val binding = MovieListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListAdapter.CustomViewHolder, position: Int) {
        val movies = movies[position]

        holder.ivMovie.load(movies.movieImageUrl)
    }

    fun updateList(filteredList: List<Movie>) {
        movies = filteredList
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}