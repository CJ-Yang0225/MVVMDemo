package com.example.jerry.mvvmdemo.api

import com.example.jerry.mvvmdemo.data.model.Comic
import com.example.jerry.mvvmdemo.data.model.ComicSearchResponse
import com.example.jerry.mvvmdemo.data.model.Galleries
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ComicService {
    @GET("/api/manga-info")
    fun searchComics(@Query("q") query: String): Call<ComicSearchResponse>

    // /api/manga-info/{id}} ex:/api/manga-info/289638
    @GET("/api/manga-info/{id}")
    fun getImgList(@Path("id") id: String): Call<Galleries>
}