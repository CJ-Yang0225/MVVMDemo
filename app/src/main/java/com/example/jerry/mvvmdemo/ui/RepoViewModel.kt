package com.example.jerry.mvvmdemo.ui

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jerry.mvvmdemo.data.DataModel
import com.example.jerry.mvvmdemo.data.model.Repo


class RepoViewModel : ViewModel() {
    val isLoading = ObservableBoolean(false)
    private val repos = MutableLiveData<List<Repo>>()
    private lateinit var dataModel: DataModel

    fun setDataModel(dataModel: DataModel?) {
        this.dataModel = dataModel!!
    }

    fun getRepos(): LiveData<List<Repo>> {
        return repos
    }

    fun searchRepo(query: String) {
        isLoading.set(true)

        dataModel.searchRepo(query, object : DataModel.OnDataReadyCallback {
            override fun onDataReady(data: List<Repo>?) {
                repos.value = data
                isLoading.set(false)
            }
        })
    }
}