package com.example.zemogaapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.databasezemoga.entity.PostEntity
import com.example.domain.PostItem
import com.example.domain.PostResponse
import com.example.domain.toEntity
import com.example.domain.toUI
import com.example.networking.utils.Result
import com.example.zemogaapp.BaseActivity
import com.example.zemogaapp.R
import com.example.zemogaapp.databinding.FragmentAllPostsBinding
import com.example.zemogaapp.ui.adapter.RecyclerItemsAdapter
import com.example.zemogaapp.view_model.LocalStorageViewModel
import com.example.zemogaapp.view_model.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class AllPostsFragment : Fragment(), RecyclerItemsAdapter.PostClickListener {

    private var _binding: FragmentAllPostsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainActivityViewModel by viewModels()
    private val localViewModel: LocalStorageViewModel by viewModels()
    private var postsItems: List<PostItem> = listOf()
    private lateinit var adapter: RecyclerItemsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        localViewModel.getLocalPosts()
        localViewModel.localItems.observe(this, Observer {data ->
            (activity as BaseActivity).dismissLoading()
            postsItems = data.map { it.toUI() }
            if (postsItems.isNotEmpty()){
                setupAdapter(postsItems)
            }else{
                viewModel.getPosts()
            }
        })

        viewModel.responseUi.observe(this, {
            handleResult(it)
        })

    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).showReloadToolbar { viewModel.getPosts() }
    }

    private fun handleResult(result: Result<List<PostResponse>>?) {
        result?.let {
            when (result) {
                is Result.Success -> {
                    val dataResponse = result.data
                    postsItems = dataResponse.map { it.toUI() }
                    setFirstTwentyPostsAsUnRead()
                    setupAdapter(postsItems)
                    insertAllItemsInDb(postsItems)
                    (activity as BaseActivity).dismissLoading()
                }
                is Result.Failure -> {
                    Log.i("Error", "Error")
                    (activity as BaseActivity).dismissLoading()
                }
                is Result.Loading -> {
                    Log.i("Loading", "Loading")
                    (activity as BaseActivity).showLoading()
                }
            }
        }

    }

    private fun insertAllItemsInDb(data : List<PostItem>){
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        scope.launch {
            localViewModel.insertAllPosts(data)
        }
    }

    private fun setFirstTwentyPostsAsUnRead(){
        if(postsItems.size > 20){
            for (i in 0 .. 19 ) {
                postsItems[i].isRead = false
            }
        }
    }

    private fun setupAdapter(items: List<PostItem>) {
        adapter = RecyclerItemsAdapter(requireContext(), items)
        adapter.setClickListener(this)
        binding.recyclerAllPosts.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        binding.recyclerAllPosts.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerAllPosts.adapter = adapter
    }

    override fun onPostItemClick(item: PostItem) {
        item.isRead = true
        val readPost = item.toEntity()
        localViewModel.updatePosts(readPost)
        findNavController().navigate(AllPostsFragmentDirections.actionAllPostsFragmentToPostDetailActivity(item))
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            AllPostsFragment()
    }
}