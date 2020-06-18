package com.example.jerry.mvvmdemo.data

import android.util.Log
import com.example.jerry.mvvmdemo.api.ComicManager
import com.example.jerry.mvvmdemo.data.model.Comic
import com.example.jerry.mvvmdemo.data.model.ComicSearchResponse
import com.example.jerry.mvvmdemo.ui.ComicActivity
import retrofit2.Call
import retrofit2.Response


class ComicModel {
    private val apiService = ComicManager().getAPI()

    fun searchComic(query: String, callback: OnDataReadyCallback) {
        Log.d(javaClass.toString(), "searchComic: ${apiService}")
        apiService.searchComics(query)
                .enqueue(object : retrofit2.Callback<ComicSearchResponse> {
                    override fun onResponse(call: Call<ComicSearchResponse>?, response: Response<ComicSearchResponse>) {
//                        response.body()?.repoIds?.let { callback.onDataReady(it) }
                        Log.d(javaClass.toString(), "onResponse_code: ${response.code()} Data: ${response.body()}")
                        callback.onDataReady(response.body()?.mangaInfoList)
                    }

                    override fun onFailure(call: Call<ComicSearchResponse>, t: Throwable) { }
                })
    }
    
    interface OnDataReadyCallback {
        fun onDataReady(data: List<Comic>?)
    }
}