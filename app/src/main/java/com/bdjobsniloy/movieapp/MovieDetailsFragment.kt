package com.bdjobsniloy.movieapp

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bdjobsniloy.movieapp.databinding.FragmentMovieDetailsBinding
import com.bdjobsniloy.movieapp.entities.Bookmark
import com.bdjobsniloy.movieapp.viewmodels.BookmarkViewModel
import com.bdjobsniloy.movieapp.viewmodels.MovieViewModel
import com.bumptech.glide.Glide


class MovieDetailsFragment : Fragment() {
    private lateinit var binding:FragmentMovieDetailsBinding
    private val movieViewModel: MovieViewModel by activityViewModels()
    private val bookmarkViewModel: BookmarkViewModel by activityViewModels()
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding =  FragmentMovieDetailsBinding.inflate(inflater, container, false)

        val movie_id = arguments?.getInt("movie_id")

        //Fetch
        movieViewModel.fetchMovie(movie_id!!)

        bookmarkViewModel.bookmarkExits(movie_id)
        bookmarkViewModel.bookmarkExitsLD.observe(viewLifecycleOwner){
            if (it){
                binding.checkBookmark.setImageDrawable(resources.getDrawable(R.drawable.ic_bookmark_yes))
            }else{
                binding.checkBookmark.setImageDrawable(resources.getDrawable(R.drawable.ic_bookmark_no))
            }
            bookmarkViewModel.bookmarkExits(movie_id)
        }


        movieViewModel.movieLD.observe(viewLifecycleOwner) {movie ->

            Glide.with(requireActivity())
                .load("https://image.tmdb.org/t/p/w500${movie.backdrop_path}")
                .placeholder(R.drawable.loading)
                .into(binding.movieImgPreview)

            binding.movieName.text = movie.title
            binding.movieDes.text = movie.overview


            movie.spoken_languages.forEach {
                if (it.name.isNotEmpty()){
                    binding.movieLanguage.text = it.name
                    return@forEach
                }
            }

            binding.rating.text = "${"%.1f".format(movie.vote_average.toFloat())}/10 IMDb"

            var tv_genre = ""

            binding.genre.removeAllViews()
            movie.genres.forEach {

                tv_genre += "${it.name},"
                val dynamicTextview = TextView(requireActivity())
                dynamicTextview.text = it.name
                dynamicTextview.setBackgroundResource(R.drawable.genre_back)
                dynamicTextview.textSize = 11.0F
                dynamicTextview.setTextColor(Color.parseColor("#5A6CCF"))

                val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                params.setMargins(0, 0, 10, 0)
                dynamicTextview.layoutParams = params

                binding.genre.addView(dynamicTextview)

            }

            val hour = movie.runtime / 60
            val minutes = movie.runtime % 60

            binding.movieLength.text = "$hour h $minutes min"


            //Save to bookmark
            binding.checkBookmark.setOnClickListener {
                val bookmark = Bookmark(
                    id = movie.id,
                    title = movie.title,
                    rating = movie.vote_average.toFloat(),
                    url = movie.poster_path!!,
                    genre = tv_genre,
                    runtime = "$hour h $minutes min"
                )
                bookmarkViewModel.addToBookmark(bookmark) {task,id->
                    if (task=="Success"){
                        Toast.makeText(requireActivity(), "Added to Bookmark", Toast.LENGTH_SHORT).show()
                    }else{
                        bookmarkViewModel.removeMovie(movie.id){
                            if (it=="Success"){
                                Toast.makeText(requireActivity(), "Remove from the Bookmark", Toast.LENGTH_SHORT).show()
                            }
                        }

                    }
                }
            }

        }






        return binding.root
    }

}