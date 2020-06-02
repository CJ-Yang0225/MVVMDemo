package com.example.jerry.mvvmdemo.data.model

data class RepoSearchResponse(
    val query: String,
    val repoIds: List<Repo>?,
    val totalCount: Int
)

