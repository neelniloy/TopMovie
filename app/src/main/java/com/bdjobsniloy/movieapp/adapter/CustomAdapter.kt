package com.bdjobsniloy.movieapp.adapter

import android.graphics.Color
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bdjobsniloy.movieapp.R
import com.bdjobsniloy.movieapp.viewmodels.GenreViewModel
import com.bumptech.glide.Glide
import kotlin.math.roundToInt

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
    tv.text = "${"%.1f".format(rate.toFloat())}/10 IMDb"
}

@BindingAdapter("app:setGenreView")
fun setGenreView(layout: LinearLayout, text: String) {

    val strs = text.split(",").toTypedArray()

    layout.removeAllViews()
    strs.forEach {

        if (it.isNotEmpty()){
            val dynamicTextview = TextView(layout.context)
            dynamicTextview.text = it
            dynamicTextview.setBackgroundResource(R.drawable.genre_back)
            dynamicTextview.textSize = 10.0F
            dynamicTextview.setTextColor(Color.parseColor("#5A6CCF"))

            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(0, 0, 10, 0)
            dynamicTextview.layoutParams = params

            layout.addView(dynamicTextview)
        }

    }
}