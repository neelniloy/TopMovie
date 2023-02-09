package com.bdjobsniloy.movieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bdjobsniloy.movieapp.adapter.BookmarkAdapter
import com.bdjobsniloy.movieapp.adapter.NowShowingAdapter
import com.bdjobsniloy.movieapp.databinding.FragmentBookmarksBinding
import com.bdjobsniloy.movieapp.entities.Bookmark
import com.bdjobsniloy.movieapp.model.NowShowing
import com.bdjobsniloy.movieapp.viewmodels.BookmarkViewModel
import com.bdjobsniloy.movieapp.viewmodels.MovieViewModel

class BookmarksFragment : Fragment() {

    private lateinit var binding:FragmentBookmarksBinding
    private val bookmarkViewModel: BookmarkViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentBookmarksBinding.inflate(inflater, container, false)

        binding.drawerIcon.setOnClickListener{
            (activity as MainActivity).openCloseNavigationDrawer()
        }

        val adapter = BookmarkAdapter {binding,bookmark,position->

            binding.cardBookmark.setOnClickListener {
                findNavController().navigate(R.id.action_bookmarksFragment_to_movieDetailsFragment,args = bundleOf("movie_id" to bookmark.id))
            }

            //Save to bookmark
            binding.delete.setOnClickListener {
                bookmarkViewModel.removeMovie(bookmark.id){
                    if (it=="Success"){
                        bookmarkViewModel.getMovieList()
                        Toast.makeText(requireActivity(), "Remove from the Bookmark", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

        val layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        binding.bookmarkRv.layoutManager = layoutManager
        binding.bookmarkRv.adapter = adapter

        //Fetch
        bookmarkViewModel.getMovieList()

        bookmarkViewModel.movieLD.observe(viewLifecycleOwner) {movieList ->

            if (movieList.isEmpty()) {
                binding.mProgressBar.visibility = View.VISIBLE
            } else {
                binding.mProgressBar.visibility = View.GONE
            }

            val tempList = mutableListOf<Bookmark>()
            for (movie in movieList) {
                tempList.add(movie)
            }
            adapter.submitList(tempList)
        }

        return binding.root
    }
}