package com.example.jerry.mvvmdemo.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitManager {

    // static
    companion object {
        val mInstance = RetrofitManager()
    }

    private fun initRetrofit(): GithubService {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
//                .create(GithubService::class.java)

        return retrofit.create(GithubService::class.java)
    }

    fun getAPI(): GithubService {
        return mInstance.initRetrofit()
    }
}

