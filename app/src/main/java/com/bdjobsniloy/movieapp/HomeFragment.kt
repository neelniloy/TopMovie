package com.bdjobsniloy.movieapp

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2.ScrollState
import com.bdjobsniloy.movieapp.adapter.NowShowingAdapter
import com.bdjobsniloy.movieapp.adapter.PopularAdapter
import com.bdjobsniloy.movieapp.databinding.FragmentHomeBinding
import com.bdjobsniloy.movieapp.entities.Bookmark
import com.bdjobsniloy.movieapp.entities.Genre
import com.bdjobsniloy.movieapp.model.NowShowing
import com.bdjobsniloy.movieapp.model.Popular
import com.bdjobsniloy.movieapp.viewmodels.GenreViewModel
import com.bdjobsniloy.movieapp.viewmodels.MovieViewModel

class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding
    private val movieViewModel: MovieViewModel by activityViewModels()
    private val genreViewModel: GenreViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentHomeBinding.inflate(inflater, container, false)

        binding.notificationIcon.setOnClickListener{
            Toast.makeText(requireActivity(), "Feature will added soon...", Toast.LENGTH_SHORT).show()
        }

        binding.drawerIcon.setOnClickListener{
            (activity as MainActivity).openCloseNavigationDrawer()
        }


        //Fetch Genre
        genreViewModel.genreExits()
        genreViewModel.genreExitsLD.observe(viewLifecycleOwner){
            if (it){

            }else{
                movieViewModel.fetchGenreList()
                movieViewModel.genreLD.observe(viewLifecycleOwner) {g ->

                    g.genres.forEach{
                        val genre = Genre(
                            id =it.id,
                            name = it.name
                        )
                        genreViewModel.addGenre(genre)
                    }

                }
            }
        }


        //Now Showing
        val adapter = NowShowingAdapter {binding,show,position->

            binding.itemNowShowing.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_movieDetailsFragment,args = bundleOf("movie_id" to show.id))
            }
            binding.cardItem.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_movieDetailsFragment,args = bundleOf("movie_id" to show.id))
            }

        }

        val layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        binding.nowShowingRv.layoutManager = layoutManager
        binding.nowShowingRv.adapter = adapter

        //Fetch
        movieViewModel.fetchNowShowing(1)

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


        //Popular
        val popularAdapter = PopularAdapter {binding,popular,position->

            binding.genreLayout.removeAllViews()

            popular.genre_ids.listIterator().forEach {
                val dynamicTextview = TextView(requireActivity())
                dynamicTextview.text = genreViewModel.getGenreName(it).value
                dynamicTextview.setBackgroundResource(R.drawable.genre_back)
                dynamicTextview.textSize = 10.0F
                dynamicTextview.setTextColor(Color.parseColor("#5A6CCF"))

                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)
                params.setMargins(0, 0, 10, 0)
                dynamicTextview.layoutParams = params

                binding.genreLayout.addView(dynamicTextview)


            }

            binding.itemPopular.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_movieDetailsFragment,args = bundleOf("movie_id" to popular.id))
            }

            binding.itemPopular.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_movieDetailsFragment,args = bundleOf("movie_id" to popular.id))
            }
            binding.cardItem.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_movieDetailsFragment,args = bundleOf("movie_id" to popular.id))
            }

        }

        val popLayoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.popularRv.layoutManager = popLayoutManager
        binding.popularRv.adapter = popularAdapter

        //Fetch
        movieViewModel.fetchPopularMovie(1)

        movieViewModel.popularLD.observe(viewLifecycleOwner) {movieList ->

            if (movieList.results.isEmpty()) {
                binding.pProgressBar.visibility = View.VISIBLE
            } else {
                binding.pProgressBar.visibility = View.GONE
            }

            val tempList = mutableListOf<Popular.Movie>()
            for (movie in movieList.results) {
                tempList.add(movie)
            }
            popularAdapter.submitList(tempList)
        }

        return binding.root
    }


}