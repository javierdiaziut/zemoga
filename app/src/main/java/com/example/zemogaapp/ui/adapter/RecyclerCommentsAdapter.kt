package com.example.zemogaapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.PostComment
import com.example.domain.PostItem
import com.example.domain.PostResponse
import com.example.zemogaapp.databinding.LayoutItemRecyclerBinding
import com.example.zemogaapp.databinding.LayoutItemRecyclerCommentsBinding

class RecyclerCommentsAdapter(private val context: Context, var items: List<PostComment>) :
    ListAdapter<PostComment, RecyclerView.ViewHolder>(UserDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderItem(
            binding = LayoutItemRecyclerCommentsBinding.inflate(LayoutInflater.from(context),
                parent, false))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderItem).bind(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolderItem internal constructor(val binding: LayoutItemRecyclerCommentsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.textCommentDescription.text = items[position].body
        }

    }

    private class UserDiffCallBack : DiffUtil.ItemCallback<PostComment>() {
        override fun areItemsTheSame(oldItem: PostComment, newItem: PostComment): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: PostComment, newItem: PostComment): Boolean =
            oldItem == newItem
    }

}