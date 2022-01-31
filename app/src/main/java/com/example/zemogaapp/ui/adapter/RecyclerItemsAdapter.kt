package com.example.zemogaapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.PostResponse
import com.example.zemogaapp.databinding.LayoutItemRecyclerBinding

class RecyclerItemsAdapter(val context: Context, var items: ArrayList<PostResponse>) :
    ListAdapter<PostResponse, RecyclerView.ViewHolder>(UserDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderItem(
            binding = LayoutItemRecyclerBinding.inflate(LayoutInflater.from(context),
                parent, false))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderItem).bind(position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolderItem internal constructor(val binding: LayoutItemRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(position: Int) {
            binding.textPostDescription.text = items[position].title
        }

        override fun onClick(view: View) {

        }
    }

    private class UserDiffCallBack : DiffUtil.ItemCallback<PostResponse>() {
        override fun areItemsTheSame(oldItem: PostResponse, newItem: PostResponse): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: PostResponse, newItem: PostResponse): Boolean =
            oldItem == newItem
    }
}