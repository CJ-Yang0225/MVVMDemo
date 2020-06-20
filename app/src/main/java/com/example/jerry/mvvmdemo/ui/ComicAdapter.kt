package com.example.jerry.mvvmdemo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.jerry.mvvmdemo.data.model.Comic
import com.example.jerry.mvvmdemo.data.model.Repo
import com.example.jerry.mvvmdemo.databinding.ComicItemBinding
import com.example.jerry.mvvmdemo.databinding.RepoItemBinding


/* 連接 VM 獲得的後端資料(Model)和前端介面(View) */
class ComicAdapter(private val items: ArrayList<Comic>) : RecyclerView.Adapter<ComicAdapter.ComicViewHolder>() {
    private lateinit var onItemClickListener: OnItemClickListener



    // 設定 binding 初始值
    class ComicViewHolder(private val binding: ComicItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(comic: Comic) {
            binding.comic = comic
            binding.executePendingBindings()
        }
    }

    // 負責承載每個子項的佈局
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ComicItemBinding.inflate(layoutInflater, parent, false)

        return ComicViewHolder(binding)
    }

    // 獲得資料長度
    override fun getItemCount(): Int {
        return items.size
    }

    // 負責將每個子項holder綁定數據
    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val (_, title, thumb) = items[position]
        holder.bind(items[position])

        // 為每個 Item 建立點擊事件監聽器
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(it, position)
        }
    }

    // 清除 Items
    fun clearItems() {
        val size: Int = this.items.size
        this.items.clear()

        // RecyclerView 刪除
        notifyItemRangeRemoved(0, size)
    }

    // 置換 Items
    fun swapItems(newItems: List<Comic>?) {
        // DiffUtil.DiffResult 比對數據後，返回差異結果
        val result: DiffUtil.DiffResult = DiffUtil.calculateDiff(RepoDiffCallback(this.items, newItems))
        if (newItems != null) {
            this.items.clear()
            this.items.addAll(newItems)
            result.dispatchUpdatesTo(this)
        }
    }

    // Setter
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    // RecyclerView 點擊接口
    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
        fun onItemLongClick(view: View, position: Int)
    }

    open class RepoDiffCallback(private val oldList: List<Comic>?, private val newList: List<Comic>?) : DiffUtil.Callback() {

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