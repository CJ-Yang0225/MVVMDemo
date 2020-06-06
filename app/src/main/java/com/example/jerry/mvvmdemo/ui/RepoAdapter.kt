package com.example.jerry.mvvmdemo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jerry.mvvmdemo.data.model.Repo
import com.example.jerry.mvvmdemo.databinding.RepoItemBinding


internal data class RepoAdapter(var items: ArrayList<Repo>) : RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    // 設定 binding 初始值
    internal class RepoViewHolder(internal val binding: RepoItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RepoItemBinding.inflate(layoutInflater, parent, false)

        return RepoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        // In Java: Repo repo = items.get(position);
        // id & name are never used, so replace with "_"
        val (_, _, fullName, description, stars, owner) = items[position]

        Glide.with(holder.itemView.context)
                .load(owner.avatarUrl)
                .into(holder.binding.ownerAvatar)
        holder.binding.name.text = fullName
        holder.binding.desc.text = description
        holder.binding.stars.text = stars.toString()
    }

    fun clearItems() {
        val size: Int = this.items.size
        this.items.clear()
        notifyItemRangeRemoved(0, size)
    }

    fun swapItems(newItems: List<Repo>?) {
        // DiffUtil.DiffResult 比對數據後，返回差異結果
        val result: DiffUtil.DiffResult = DiffUtil.calculateDiff(RepoDiffCallback(this.items, newItems))
        if (newItems != null) {
            this.items.clear()
            this.items.addAll(newItems)
            result.dispatchUpdatesTo(this)
        }
    }

    open class RepoDiffCallback(private val oldList: List<Repo>?, private val newList: List<Repo>?) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldList?.size ?: 0
        }

        override fun getNewListSize(): Int {
            return newList?.size ?: 0
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            if (oldList.isNullOrEmpty() || newList.isNullOrEmpty()) return false
            val oldId = oldList[oldItemPosition].id
            val newId = newList[newItemPosition].id
            return oldId == newId
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            if (oldList.isNullOrEmpty() || newList.isNullOrEmpty()) return false
            val oldRepo = oldList[oldItemPosition]
            val newRepo = newList[newItemPosition]
            return oldRepo === newRepo
        }
    }
}
