package com.bdjobsniloy.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bdjobsniloy.movieapp.databinding.NowShowingItemBinding
import com.bdjobsniloy.movieapp.model.NowShowing

class NowShowingAdapter(val callback: (NowShowingItemBinding,NowShowing.Movie,Int)->Unit) : ListAdapter<NowShowing.Movie, NowShowingAdapter.NowShowingViewHolder>(
    MovieDiffUtil()
){

    class NowShowingViewHolder(val binding: NowShowingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(show: NowShowing.Movie) {
            binding.show = show
        }
    }

    class MovieDiffUtil : DiffUtil.ItemCallback<NowShowing.Movie>() {
        override fun areItemsTheSame(oldItem: NowShowing.Movie, newItem: NowShowing.Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NowShowing.Movie, newItem: NowShowing.Movie): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowShowingViewHolder {
        val binding = NowShowingItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return NowShowingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NowShowingViewHolder, position: Int) {
        val show = getItem(position)

        holder.bind(show)
        callback(holder.binding,show,position)


    }
}