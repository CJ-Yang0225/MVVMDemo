package com.example.jerry.mvvmdemo.ui

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class RepoBindings {
    // static void in Kotlin
    companion object {
        @BindingAdapter("imageUrl") // Custom setter(Data Binding)
        @JvmStatic
        fun bindImage(imageView: ImageView, url: String?) {
            val context: Context = imageView.context
            Glide.with(context)
                    .load(url)
                    .into(imageView)
        }
    }
}