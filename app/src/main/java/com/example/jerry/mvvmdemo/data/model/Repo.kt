package com.example.jerry.mvvmdemo.data.model

data class Repo(
    val id: Int,
    val name: String,
    val full_name: String,
    val description: String,
    val stargazers_count: Int,
    val owner: Owner
)