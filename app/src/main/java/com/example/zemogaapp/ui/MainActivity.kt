package com.example.zemogaapp.ui

import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import com.example.domain.PostResponse
import com.example.networking.utils.Result
import com.example.zemogaapp.BaseActivity
import com.example.zemogaapp.R
import com.example.zemogaapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        handleTabListeners()
    }

    private fun handleTabListeners(){
        binding.mainTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 ->{
                        findNavController(R.id.activityMainNavHostFragment).navigate(R.id.allPostsFragment)
                    }
                    1 ->{
                        findNavController(R.id.activityMainNavHostFragment).navigate(R.id.favoritePostsFragment)
                    }
                    else ->{
                        findNavController(R.id.activityMainNavHostFragment).navigate(R.id.allPostsFragment)
                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselect
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselect
            }
        })
    }


}