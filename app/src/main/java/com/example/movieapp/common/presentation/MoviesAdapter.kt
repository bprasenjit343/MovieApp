package com.example.movieapp.common.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.common.domain.model.movies.Movies
import com.example.movieapp.common.utils.BASE_IMAGE_URL
import com.example.movieapp.common.utils.setImage
import com.example.movieapp.databinding.RecyclerViewMovieItemBinding

class MoviesAdapter : ListAdapter<Movies, MoviesAdapter.MoviesViewHolder>(ITEM_COMPARATOR) {


    inner class MoviesViewHolder(
        private val binding: RecyclerViewMovieItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Movies) {
            binding.movieTitle.text = item.title
            binding.movieRating.text = "${item.rating}"
            binding.movieImage.setImage("${BASE_IMAGE_URL}/${item.imageUrl}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding =
            RecyclerViewMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item: Movies = getItem(position)
        holder.bind(item)
    }
}

private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<Movies>() {
    override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
        return oldItem == newItem
    }
}