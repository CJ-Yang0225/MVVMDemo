package com.example.jerry.mvvmdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jerry.mvvmdemo.data.ComicModel
import com.example.jerry.mvvmdemo.ui.ComicViewModel


/* 生成 ViewModel 實體物件 */
class ComicViewModelFactory : ViewModelProvider.Factory {
    private var comicModel: ComicModel = ComicModel()

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ComicViewModel::class.java)) {
            return ComicViewModel(comicModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}