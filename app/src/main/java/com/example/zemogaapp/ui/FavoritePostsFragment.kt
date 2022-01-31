package com.example.zemogaapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.PostItem
import com.example.domain.toEntity
import com.example.domain.toUI
import com.example.zemogaapp.BaseActivity
import com.example.zemogaapp.databinding.FragmentFavoritePostsBinding
import com.example.zemogaapp.ui.adapter.RecyclerItemsAdapter
import com.example.zemogaapp.view_model.LocalStorageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritePostsFragment : Fragment(), RecyclerItemsAdapter.PostClickListener {

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
        localViewModel.localItems.observe(this, Observer { data ->
            (activity as BaseActivity).dismissLoading()
            postsItems = data.map { it.toUI() }
            setupAdapter(postsItems)
        })

        (activity as MainActivity).hideReloadToolbar()
    }

    private fun setupAdapter(items: List<PostItem>) {
        adapter = RecyclerItemsAdapter(requireContext(), items.toMutableList())
        adapter.setClickListener(this)
        binding.recyclerFavoritesPosts.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
        binding.recyclerFavoritesPosts.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerFavoritesPosts.adapter = adapter

        val itemTouchHelperCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    adapter.removeAt(viewHolder.adapterPosition)
                }

            }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerFavoritesPosts)
    }

    override fun onPostItemClick(item: PostItem) {
        findNavController().navigate(
            FavoritePostsFragmentDirections.actionFavoritePostsFragmentToPostDetailActivity(
                item
            )
        )
    }

    override fun onRemoveItem(item: PostItem) {
        localViewModel.deletePost(item.toEntity())
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            FavoritePostsFragment()
    }
}