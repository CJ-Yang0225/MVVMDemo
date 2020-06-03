package com.example.jerry.mvvmdemo.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jerry.mvvmdemo.data.model.Repo


data class RepoAdapter(var items: ArrayList<Repo>) : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    fun clearItems() {
        val size: Int = this.items.size
        this.items.clear()
        notifyItemRangeRemoved(0, size)
    }
}
