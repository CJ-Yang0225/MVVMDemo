package com.example.jerry.mvvmdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jerry.mvvmdemo.data.DataModel
import com.example.jerry.mvvmdemo.ui.RepoViewModel


class GithubViewModelFactory : ViewModelProvider.Factory {
        private lateinit var dataModel: DataModel
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        this.dataModel = DataModel()
        if (modelClass.isAssignableFrom(RepoViewModel::class.java)) {
            return RepoViewModel().setDataModel(dataModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
