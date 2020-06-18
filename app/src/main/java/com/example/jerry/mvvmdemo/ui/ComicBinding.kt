package com.example.jerry.mvvmdemo.ui

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class ComicBinding {
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

        @BindingAdapter("visibleGone")
        @JvmStatic
        fun showHide(view: View, show: Boolean) {
            view.visibility = if (show) View.VISIBLE else View.GONE
        }
    }
}