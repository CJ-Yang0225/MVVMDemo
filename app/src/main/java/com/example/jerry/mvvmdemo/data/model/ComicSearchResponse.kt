package com.example.jerry.mvvmdemo.data.model

data class ComicSearchResponse(
        var mangaInfoList: List<Comic>?,
        var lastPage: Int
)