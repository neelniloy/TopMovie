package com.bdjobsniloy.movieapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bdjobsniloy.movieapp.adapter.NowShowingAdapter
import com.bdjobsniloy.movieapp.adapter.PopularAdapter
import com.bdjobsniloy.movieapp.databinding.FragmentHomeBinding
import com.bdjobsniloy.movieapp.entities.Genre
import com.bdjobsniloy.movieapp.model.NowShowing
import com.bdjobsniloy.movieapp.model.Popular
import com.bdjobsniloy.movieapp.viewmodels.GenreViewModel
import com.bdjobsniloy.movieapp.viewmodels.MovieViewModel

class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding
    private val movieViewModel: MovieViewModel by activityViewModels()
    private val genreViewModel: GenreViewModel by activityViewModels()

    private var nowShowingPageNum = 1
    var nowShowingMovieList = mutableListOf<NowShowing.Movie>()
    var isLoadingNowShowing = false

    private var popularPageNum = 1
    var popularMovieList = mutableListOf<Popular.Movie>()
    var isLoadingPopular = false


    @SuppressLint("SuspiciousIndentation")
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
                            genre_id =it.id,
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
        binding.nowShowingRv.isNestedScrollingEnabled = false

        //Fetch
        movieViewModel.fetchNowShowing(nowShowingPageNum)

        // RecyclerView Pagination
        binding.nowShowingRv.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = layoutManager.childCount
                val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                val total = adapter.itemCount

                if (!isLoadingNowShowing) {

                    if ((visibleItemCount + pastVisibleItem) >= total) {
                        nowShowingPageNum++
                        movieViewModel.fetchNowShowing(nowShowingPageNum)
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })

        movieViewModel.nowShowingLD.observe(viewLifecycleOwner) {movieList ->

            if (movieList.results.isEmpty()) {
                binding.mProgressBar.visibility = View.VISIBLE
            } else {
                binding.mProgressBar.visibility = View.GONE
            }

            for (movie in movieList.results) {
                nowShowingMovieList.add(movie)
            }
            adapter.submitList(nowShowingMovieList.distinct())
        }




        //Popular
        val popularAdapter = PopularAdapter {binding,popular,position->

            binding.genreLayout.removeAllViews()
            val checkGenre = mutableListOf<String>()


                genreViewModel.getGenreList().observe(viewLifecycleOwner){gList->
                    popular.genre_ids.forEach {id->
                        gList.listIterator().forEach {genre->
                            if (id==genre.genre_id){
                                val dynamicTextview = TextView(requireActivity())
                                dynamicTextview.text = genre.name
                                dynamicTextview.setBackgroundResource(R.drawable.genre_back)
                                dynamicTextview.textSize = 10.0F
                                dynamicTextview.setTextColor(Color.parseColor("#5A6CCF"))

                                val params = LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.WRAP_CONTENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT)
                                params.setMargins(0, 0, 10, 0)
                                dynamicTextview.layoutParams = params

                                if (!checkGenre.contains(genre.name)){
                                    binding.genreLayout.addView(dynamicTextview)
                                    checkGenre.add(genre.name)
                                }else{
                                    binding.genreLayout.removeView(dynamicTextview)
                                }
                            }
                        }
                    }
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
        binding.popularRv.isNestedScrollingEnabled = false

        //Fetch
        movieViewModel.fetchPopularMovie(popularPageNum)

/*        // RecyclerView Pagination
        binding.popularRv.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount = layoutManager.childCount
                val pastVisibleItem = layoutManager.findFirstCompletelyVisibleItemPosition()
                val total = adapter.itemCount

                if (!isLoadingPopular) {

                    if ((visibleItemCount + pastVisibleItem) >= total) {
                        //Toast.makeText(requireActivity(), "Last Page", Toast.LENGTH_SHORT).show()
                        popularPageNum++
                        movieViewModel.fetchPopularMovie(popularPageNum)
                    }
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })*/


        binding.nestedScroll.setOnScrollChangeListener { v: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if (v.getChildAt(v.childCount - 1) != null) {
                if (scrollY >= v.getChildAt(v.childCount - 1)
                        .measuredHeight - v.measuredHeight &&
                    scrollY > oldScrollY
                ) {
//                    Toast.makeText(requireActivity(), "Scrolling Nested...", Toast.LENGTH_SHORT)
//                        .show()
                    popularPageNum++
                    movieViewModel.fetchPopularMovie(popularPageNum)

                }
            }
        }

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
            popularMovieList.addAll(tempList)
            popularAdapter.submitList(popularMovieList.distinct())
            popularAdapter.notifyItemChanged(tempList.size)
        }

        return binding.root
    }


}