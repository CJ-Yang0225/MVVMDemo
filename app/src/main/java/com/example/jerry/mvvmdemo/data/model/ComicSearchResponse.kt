package com.example.jerry.mvvmdemo.data.model

/* 搜尋結果的JSON資料 */
data class ComicSearchResponse(
    var mangaInfoList: List<Comic>?,
    var lastPage: Int
)