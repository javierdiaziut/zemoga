package com.example.zemogaapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.PostItem
import com.example.domain.PostResponse
import com.example.zemogaapp.databinding.LayoutItemRecyclerBinding

class RecyclerItemsAdapter(private val context: Context, var items: MutableList<PostItem>) :
    ListAdapter<PostItem, RecyclerView.ViewHolder>(UserDiffCallBack()) {

    private var clickListener: PostClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderItem(
            binding = LayoutItemRecyclerBinding.inflate(LayoutInflater.from(context),
                parent, false))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolderItem).bind(position)
        holder.itemView.setOnClickListener {
            clickListener?.onPostItemClick(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolderItem internal constructor(val binding: LayoutItemRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.textPostDescription.text = items[position].title

            if(items[position].isRead){
                binding.dotUnreadPost.visibility = View.GONE
            }else {
                binding.dotUnreadPost.visibility = View.VISIBLE
            }

            if(items[position].isFavorite){
                binding.imageFavorite.visibility = View.VISIBLE
            }else {
                binding.imageFavorite.visibility = View.GONE
            }
        }

    }

    private class UserDiffCallBack : DiffUtil.ItemCallback<PostItem>() {
        override fun areItemsTheSame(oldItem: PostItem, newItem: PostItem): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: PostItem, newItem: PostItem): Boolean =
            oldItem == newItem
    }

    interface PostClickListener {
        fun onPostItemClick(item : PostItem)
        fun onRemoveItem(item : PostItem)
    }

    internal fun setClickListener(clickListener: PostClickListener) {
        this.clickListener = clickListener
    }

    fun removeAt(position: Int) {
        clickListener?.onRemoveItem(item = items[position])
        items.removeAt(position)
        notifyItemRemoved(position)
    }
}