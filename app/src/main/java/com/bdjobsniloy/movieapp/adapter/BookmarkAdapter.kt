package com.bdjobsniloy.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bdjobsniloy.movieapp.databinding.BookmarksItemBinding
import com.bdjobsniloy.movieapp.databinding.PopularMovieItemBinding
import com.bdjobsniloy.movieapp.entities.Bookmark
import com.bdjobsniloy.movieapp.model.Popular

class BookmarkAdapter(val callback: (BookmarksItemBinding, Bookmark, Int)->Unit) : ListAdapter<Bookmark, BookmarkAdapter.BookmarkViewHolder>(
    MovieDiffUtil()
){

    class BookmarkViewHolder(val binding: BookmarksItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(bookmark: Bookmark) {
            binding.bookmark = bookmark
        }
    }

    class MovieDiffUtil : DiffUtil.ItemCallback<Bookmark>() {
        override fun areItemsTheSame(oldItem: Bookmark, newItem: Bookmark): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Bookmark, newItem: Bookmark): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val binding = BookmarksItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return BookmarkViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val bookmark = getItem(position)

        holder.bind(bookmark)
        callback(holder.binding,bookmark,position)


    }
}