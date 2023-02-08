package com.bdjobsniloy.movieapp.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bdjobsniloy.movieapp.R
import com.bumptech.glide.Glide

@BindingAdapter("app:setImageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView.context)
            .load("https://image.tmdb.org/t/p/w300$url")
            .placeholder(R.drawable.loading)
            .into(imageView)
    }
}

@BindingAdapter("app:setRating")
fun setRating(tv: TextView, rate: Number) {
    tv.text = "${rate}/10 IMDb"
}