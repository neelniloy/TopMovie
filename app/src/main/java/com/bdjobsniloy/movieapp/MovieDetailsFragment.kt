package com.bdjobsniloy.movieapp

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bdjobsniloy.movieapp.databinding.FragmentMovieDetailsBinding





class MovieDetailsFragment : Fragment() {
    private lateinit var binding:FragmentMovieDetailsBinding
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding =  FragmentMovieDetailsBinding.inflate(inflater, container, false)



        return binding.root
    }

}