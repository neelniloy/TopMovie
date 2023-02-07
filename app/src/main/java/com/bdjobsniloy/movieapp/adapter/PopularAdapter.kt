package com.bdjobsniloy.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bdjobsniloy.movieapp.databinding.NowShowingItemBinding
import com.bdjobsniloy.movieapp.databinding.PopularMovieItemBinding
import com.bdjobsniloy.movieapp.model.NowShowing
import com.bdjobsniloy.movieapp.model.Popular

class PopularAdapter(val callback: (PopularMovieItemBinding,Popular.Movie,Int)->Unit) : ListAdapter<Popular.Movie, PopularAdapter.PopularViewHolder>(
    MovieDiffUtil()
){

    class PopularViewHolder(val binding: PopularMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(popular: Popular.Movie) {
            binding.popular = popular
        }
    }

    class MovieDiffUtil : DiffUtil.ItemCallback<Popular.Movie>() {
        override fun areItemsTheSame(oldItem: Popular.Movie, newItem: Popular.Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Popular.Movie, newItem: Popular.Movie): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val binding = PopularMovieItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PopularViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val popular = getItem(position)

        holder.bind(popular)
        callback(holder.binding,popular,position)


    }
}