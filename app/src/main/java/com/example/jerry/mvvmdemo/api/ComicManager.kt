package com.example.jerry.mvvmdemo.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ComicManager {
    // static
    companion object {
        val mInstance = ComicManager()
    }

    /* 利用 Retrofit 對API建立連線初始化 */
    private fun initRetrofit(): ComicService {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://community-candle.hopto.org:3000/")     // https://api.github.com/
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(ComicService::class.java)
    }

    fun getAPI(): ComicService {
        return mInstance.initRetrofit()
    }
}