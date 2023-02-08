package com.bdjobsniloy.movieapp

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bdjobsniloy.movieapp.databinding.FragmentMovieDetailsBinding
import com.bdjobsniloy.movieapp.model.NowShowing
import com.bdjobsniloy.movieapp.viewmodels.MovieViewModel
import com.bumptech.glide.Glide


class MovieDetailsFragment : Fragment() {
    private lateinit var binding:FragmentMovieDetailsBinding
    private val movieViewModel: MovieViewModel by activityViewModels()
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

        movieViewModel.movieLD.observe(viewLifecycleOwner) {movie ->

            Glide.with(requireActivity())
                .load("https://image.tmdb.org/t/p/w500${movie.backdrop_path}")
                .placeholder(R.drawable.loading)
                .into(binding.movieImgPreview)

            binding.movieName.text = movie.title
            binding.movieDes.text = movie.overview


            movie.spoken_languages.forEach {
                binding.movieLanguage.text = it.name
            }

            binding.rating.text = "${movie.vote_average}/10 IMDb"


            binding.genre.removeAllViews()
            movie.genres.forEach {
                val dynamicTextview = TextView(requireActivity())
                dynamicTextview.text = it.name
                binding.genre.addView(dynamicTextview)

            }

            val hour = movie.runtime / 60
            val minutes = movie.runtime % 60

            binding.movieLength.text = "$hour h $minutes min"


        }


        return binding.root
    }

}