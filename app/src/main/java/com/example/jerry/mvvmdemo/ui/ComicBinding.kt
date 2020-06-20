package com.example.jerry.mvvmdemo.ui

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/* Data Binding - Custom setter */
class ComicBinding {
    // static void in Kotlin
    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun bindImage(imageView: ImageView, url: String?) {
            val context: Context = imageView.context
            Glide.with(context)
                    .load(url)
                    .into(imageView)
        }

        @JvmStatic
        @BindingAdapter("visibleGone")
        fun showHide(view: View, show: Boolean) {
            view.visibility = if (show) View.VISIBLE else View.GONE
        }
    }
}