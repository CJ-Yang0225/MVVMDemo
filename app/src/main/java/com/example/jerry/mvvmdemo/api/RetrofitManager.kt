package com.example.jerry.mvvmdemo.api

import com.example.jerry.mvvmdemo.ui.RepoViewModel
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitManager {
    companion object {
        var mInstance = RetrofitManager()
    }

    lateinit var mViewModel: RepoViewModel
    lateinit var githubService: GithubService

    private fun initRetrofit() {
        // Model
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
//                .create(GithubService::class.java)

        githubService = retrofit.create(GithubService::class.java)
    }

    fun getAPI(): GithubService {
        return mInstance.githubService
    }
}