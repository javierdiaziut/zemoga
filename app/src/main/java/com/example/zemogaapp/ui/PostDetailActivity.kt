package com.example.zemogaapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.*
import com.example.networking.utils.Result
import com.example.zemogaapp.BaseActivity
import com.example.zemogaapp.databinding.ActivityPostDetailBinding
import com.example.zemogaapp.ui.adapter.RecyclerCommentsAdapter
import com.example.zemogaapp.ui.adapter.RecyclerItemsAdapter
import com.example.zemogaapp.view_model.DetailPostViewModel
import dagger.hilt.android.AndroidEntryPoint

const val CURRENT_POST = "currentItem"

@AndroidEntryPoint
class PostDetailActivity : BaseActivity() {

    private var currentPost : PostItem? = null
    private val viewModel: DetailPostViewModel by viewModels()
    private var commentsItems: List<PostComment> = listOf()
    private lateinit var adapter: RecyclerCommentsAdapter

    private lateinit var binding: ActivityPostDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        currentPost = intent?.getParcelableExtra(CURRENT_POST)
        initViews()
        currentPost?.let {
            viewModel.getPostComments(it.id)
        }

        viewModel.responseUi.observe(this, {
            handleResult(it)
        })

    }

    private fun handleResult(result: Result<List<PostCommentsResponse>>?) {
        result?.let {
            when (result) {
                is Result.Success -> {
                    val dataResponse = result.data
                    commentsItems = dataResponse.map { it.toUI() }
                    setupAdapter(commentsItems)
                    dismissLoading()
                }
                is Result.Failure -> {
                    dismissLoading()
                }
                is Result.Loading -> {
                    showLoading()
                }
            }
        }

    }

    private fun setupAdapter(items: List<PostComment>) {
        adapter = RecyclerCommentsAdapter(this, items)

        binding.recyclerAllComments.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
        binding.recyclerAllComments.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerAllComments.adapter = adapter
    }

    private fun initViews(){
        binding.textDescription.text = currentPost?.body
    }
}