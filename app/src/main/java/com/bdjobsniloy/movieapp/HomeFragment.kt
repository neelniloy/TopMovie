package com.bdjobsniloy.movieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bdjobsniloy.movieapp.adapter.NowShowingAdapter
import com.bdjobsniloy.movieapp.databinding.FragmentHomeBinding
import com.bdjobsniloy.movieapp.model.NowShowing
import com.bdjobsniloy.movieapp.viewmodels.MovieViewModel

class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding
    private val movieViewModel: MovieViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentHomeBinding.inflate(inflater, container, false)

        binding.notificationIcon.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_movieDetailsFragment)
        }

        binding.drawerIcon.setOnClickListener{
            (activity as MainActivity).openCloseNavigationDrawer()
        }

        val adapter = NowShowingAdapter {binding,show,position->

            binding.cardNowShowing.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_movieDetailsFragment,args = bundleOf("movie_id" to show.id))
            }

        }

        val layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.nowShowingRv.layoutManager = layoutManager
        binding.nowShowingRv.adapter = adapter

        //Fetch
        movieViewModel.fetchNowShowing(5)

        movieViewModel.nowShowingLD.observe(viewLifecycleOwner) {movieList ->

            if (movieList.results.isEmpty()) {
                binding.mProgressBar.visibility = View.VISIBLE
            } else {
                binding.mProgressBar.visibility = View.GONE
            }

            val tempList = mutableListOf<NowShowing.Movie>()
            for (movie in movieList.results) {
                tempList.add(movie)
            }
            adapter.submitList(tempList)
        }

        return binding.root
    }


}