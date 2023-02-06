package com.bdjobsniloy.movieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bdjobsniloy.movieapp.databinding.FragmentBookmarksBinding

class BookmarksFragment : Fragment() {

    private lateinit var binding:FragmentBookmarksBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentBookmarksBinding.inflate(inflater, container, false)

        binding.drawerIcon.setOnClickListener{
            (activity as MainActivity).openCloseNavigationDrawer(it)
        }

        return binding.root
    }
}