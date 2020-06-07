package com.example.jerry.mvvmdemo.data

import android.util.Log
import com.example.jerry.mvvmdemo.api.RetrofitManager
import com.example.jerry.mvvmdemo.data.model.Repo
import com.example.jerry.mvvmdemo.data.model.RepoSearchResponse
import retrofit2.Call
import retrofit2.Response


class DataModel {
    private val githubService = RetrofitManager().getAPI()

    fun searchRepo(query: String, callback: OnDataReadyCallback) {
        Log.d(javaClass.toString(), "searchRepo: ${githubService}")
        githubService.searchRepos(query)
                .enqueue(object : retrofit2.Callback<RepoSearchResponse> {
                    override fun onResponse(call: Call<RepoSearchResponse>?, response: Response<RepoSearchResponse>) {
//                        response.body()?.repoIds?.let { callback.onDataReady(it) }
                        Log.d(javaClass.toString(), "onResponse_code: ${response.code()} Data: ${response.body()}")
                        callback.onDataReady(response.body()?.items)
                    }

                    override fun onFailure(call: Call<RepoSearchResponse>, t: Throwable) { }
                })
    }

    interface OnDataReadyCallback {
        fun onDataReady(data: List<Repo>?)
    }
}