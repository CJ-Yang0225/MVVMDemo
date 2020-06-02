package com.example.jerry.mvvmdemo.data.model

data class Repo(
        val id: Int,
        val name: String,
        val fullName: String,
        val description: String,
        val stars: Int,
        val owner: Owner
)