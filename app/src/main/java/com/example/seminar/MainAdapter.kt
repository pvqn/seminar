package com.example.seminar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.seminar.databinding.ItemBinding
import com.example.seminar.model.Item

class MainAdapter : PagingDataAdapter<Item, MainAdapter.MainViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
                oldItem == newItem
        }
    }

    inner class MainViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.apply {
            textView.text = item?.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            ItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}