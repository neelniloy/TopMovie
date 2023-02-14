package com.bdjobsniloy.movieapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.bdjobsniloy.movieapp.databinding.ActivityMainBinding
import org.imaginativeworld.oopsnointernet.callbacks.ConnectionCallback
import org.imaginativeworld.oopsnointernet.dialogs.signal.NoInternetDialogSignal


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


        // No Internet Dialog
        NoInternetDialogSignal.Builder(
            this,
            lifecycle
        ).apply {
            dialogProperties.apply {
                connectionCallback = object : ConnectionCallback { // Optional
                    override fun hasActiveConnection(hasActiveConnection: Boolean) {


                    }
                }
                cancelable = false // Optional
                noInternetConnectionTitle = "No Internet" // Optional
                noInternetConnectionMessage =
                    "Check your Internet connection and try again." // Optional
                showInternetOnButtons = true // Optional
                pleaseTurnOnText = "Please turn on" // Optional
                wifiOnButtonText = "Wifi" // Optional
                mobileDataOnButtonText = "Mobile data" // Optional

                onAirplaneModeTitle = "No Internet" // Optional
                onAirplaneModeMessage = "You have turned on the airplane mode." // Optional
                pleaseTurnOffText = "Please turn off" // Optional
                airplaneModeOffButtonText = "Airplane mode" // Optional
                showAirplaneModeOffButtons = true // Optional
            }
        }.build()


    }

    fun openCloseNavigationDrawer() {
        if (binding.drawerLayout.isDrawerOpen(binding.navView)) {
            binding.drawerLayout.closeDrawer(binding.navView)
        } else {
            binding.drawerLayout.openDrawer(binding.navView)
        }
    }

}