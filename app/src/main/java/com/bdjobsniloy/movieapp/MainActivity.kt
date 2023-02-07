package com.bdjobsniloy.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.bdjobsniloy.movieapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        // Call setNavigationItemSelectedListener on the NavigationView to detect when items are clicked
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.homeFragment -> {
                    if (navController.currentDestination?.id == R.id.homeFragment){
                        binding.drawerLayout.closeDrawer(binding.navView)
                    }else{
                        binding.drawerLayout.closeDrawer(binding.navView)
                        navHostFragment.findNavController().popBackStack()
                    }

                    true
                }
                R.id.bookmarksFragment -> {
                    if (navController.currentDestination?.id == R.id.bookmarksFragment){
                        binding.drawerLayout.closeDrawer(binding.navView)
                    }else{
                        binding.drawerLayout.closeDrawer(binding.navView)
                        navHostFragment.findNavController().navigate(R.id.action_homeFragment_to_bookmarksFragment)
                    }
                    true
                }
                else -> {
                    false
                }
            }

        }
    }

    fun openCloseNavigationDrawer() {
        if (binding.drawerLayout.isDrawerOpen(binding.navView)) {
            binding.drawerLayout.closeDrawer(binding.navView)
        } else {
            binding.drawerLayout.openDrawer(binding.navView)
        }
    }
}