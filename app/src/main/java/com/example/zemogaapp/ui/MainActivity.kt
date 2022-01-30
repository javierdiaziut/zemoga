package com.example.zemogaapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.domain.PostResponse
import com.example.networking.utils.Result
import com.example.zemogaapp.R
import com.example.zemogaapp.view_model.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    
    private val viewModel: MainActivityViewModel by viewModels()
    private var postsItems : List<PostResponse> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getPosts()
        viewModel.responseUi.observe(this, {
            handleResult(it)
        })
    }

    private fun handleResult(result: Result<List<PostResponse>>?) {
        result?.let {
            when (result) {
                is Result.Success -> {
                    Log.i("Success", "Success")
                    postsItems = result.data
                }
                is Result.Failure -> {
                    Log.i("Error", "Error")
                }
                is Result.Loading -> {
                    Log.i("Loading", "Loading")
                }
            }
        }

    }

}