package com.example.jerry.mvvmdemo.data

import android.util.Log
import android.widget.Toast
import com.example.jerry.mvvmdemo.api.ComicManager
import com.example.jerry.mvvmdemo.data.model.Comic
import com.example.jerry.mvvmdemo.data.model.ComicSearchResponse
import com.example.jerry.mvvmdemo.ui.ComicActivity
import retrofit2.Call
import retrofit2.Response


/* Model */
class ComicModel {
    // 呼叫 API
    private val apiService = ComicManager().getAPI()
    private val regex = Regex("\\d+\$")

    // 搜尋功能 Callback 實作
    fun searchComic(query: String, callback: OnDataReadyCallback) {
        val page = regex.find(query)?.value

        Log.d(javaClass.toString(), "searchComic: $query")
        apiService.searchComics(query, page)
                // Retrofit + RxJava 可避免 CallbackHell

                .enqueue(object : retrofit2.Callback<ComicSearchResponse> {

                    override fun onResponse(call: Call<ComicSearchResponse>?, response: Response<ComicSearchResponse>) {
//                        response.body()?.repoIds?.let { callback.onDataReady(it) }
                        Log.d(javaClass.toString(), "onResponse_code: ${response.code()} Data: ${response.body()}")
                        callback.onDataReady(response.body()?.mangaInfoList)
                    }

                    override fun onFailure(call: Call<ComicSearchResponse>, t: Throwable) { }
                })
    }

    // 檢測資料是否完成
    interface OnDataReadyCallback {
        fun onDataReady(data: List<Comic>?)
    }
}