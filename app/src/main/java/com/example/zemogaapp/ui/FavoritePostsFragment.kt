package com.example.zemogaapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.PostItem
import com.example.domain.toUI
import com.example.zemogaapp.BaseActivity
import com.example.zemogaapp.R
import com.example.zemogaapp.databinding.FragmentAllPostsBinding
import com.example.zemogaapp.databinding.FragmentFavoritePostsBinding
import com.example.zemogaapp.ui.adapter.RecyclerItemsAdapter
import com.example.zemogaapp.view_model.LocalStorageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritePostsFragment : Fragment() {

    private var _binding: FragmentFavoritePostsBinding? = null
    private val binding get() = _binding!!
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
        (activity as BaseActivity).showLoading()
        _binding = FragmentFavoritePostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        localViewModel.getFavoritesPosts()
        localViewModel.localItems.observe(this, Observer {data ->
            (activity as BaseActivity).dismissLoading()
            postsItems = data.map { it.toUI() }
            setupAdapter(postsItems)
        })
    }

    private fun setupAdapter(items: List<PostItem>) {
        adapter = RecyclerItemsAdapter(requireContext(), items)
        binding.recyclerFavoritesPosts.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        binding.recyclerFavoritesPosts.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerFavoritesPosts.adapter = adapter
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            FavoritePostsFragment()
    }
}