package com.example.jerry.mvvmdemo.api

import com.example.jerry.mvvmdemo.data.model.Repo
import com.example.jerry.mvvmdemo.data.model.RepoSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
    @GET("search/repositories")
    fun searchRepos(@Query("q") query: String): Call<RepoSearchResponse>
}