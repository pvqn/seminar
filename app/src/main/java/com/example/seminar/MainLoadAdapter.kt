package com.example.seminar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.seminar.databinding.LoadingBinding

class   MainLoadAdapter : LoadStateAdapter<MainLoadAdapter.MainLoadViewHolder>() {

    inner class MainLoadViewHolder(val binding: LoadingBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: MainLoadViewHolder, loadState: LoadState) {
        holder.binding.apply {
            progress.isVisible = loadState is LoadState.Loading
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): MainLoadViewHolder {
        return MainLoadViewHolder(
            LoadingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}