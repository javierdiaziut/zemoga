package com.example.zemogaapp.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.zemogaapp.BaseActivity
import com.example.zemogaapp.R
import com.example.zemogaapp.databinding.ActivityMainBinding
import com.example.zemogaapp.view_model.LocalStorageViewModel
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity() {


    private lateinit var binding: ActivityMainBinding
    private val localViewModel: LocalStorageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        handleTabListeners()
        initToolbar()
        handleDeleteAllPost()
    }

    private fun handleDeleteAllPost(){
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        binding.cardDeletePosts.setOnClickListener {
            scope.launch {
                localViewModel.deleteALlPosts()
            }
        }
    }

    private fun initToolbar(){
        binding.includedToolbar.labelToolbarTitle.text = getText(R.string.label_home_title)
        binding.includedToolbar.icActionToolbar.setImageResource(R.drawable.ic_download_data)

    }

    fun showReloadToolbar(reloadPosts: () -> (Unit)){
        binding.includedToolbar.icActionToolbar.visibility = View.VISIBLE
        binding.includedToolbar.icActionToolbar.setOnClickListener {
            reloadPosts()
            }
    }

    fun hideReloadToolbar() {
        binding.includedToolbar.icActionToolbar.visibility = View.GONE
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