package com.bdjobsniloy.movieapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bdjobsniloy.movieapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding:FragmentHomeBinding
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

        return binding.root
    }


}