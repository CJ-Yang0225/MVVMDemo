package com.example.jerry.mvvmdemo.ui

import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jerry.mvvmdemo.data.ComicModel
import com.example.jerry.mvvmdemo.data.model.Comic


/* ViewModel */
class ComicViewModel(private val comicModel: ComicModel) : ViewModel() {
    // Data Binding 觀察變數
    val isLoading = ObservableBoolean(false)

    private val comics = MutableLiveData<List<Comic>>()

    fun getComics(): LiveData<List<Comic>> {
        return comics
    }

    // 與 Model 溝通獲得搜尋結果的資料
    fun searchComic(query: String) {
        isLoading.set(true)

        comicModel.searchComic(query, object : ComicModel.OnDataReadyCallback {
            override fun onDataReady(data: List<Comic>?) {
                comics.value = data
                isLoading.set(false)
            }
        })
    }
}