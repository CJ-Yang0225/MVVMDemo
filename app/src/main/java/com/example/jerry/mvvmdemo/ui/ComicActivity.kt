package com.example.jerry.mvvmdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebViewClient
import com.example.jerry.mvvmdemo.R
import com.example.jerry.mvvmdemo.data.ComicModel
import kotlinx.android.synthetic.main.activity_comic.*

var comicId: Int? = null

class ComicActivity : AppCompatActivity() {

    /* 漫畫閱讀頁面，目前採用 WebView */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic)

        val webViewClient = WebViewClient()

        webView.webViewClient = webViewClient
        webView.loadUrl("http://community-candle.hopto.org:3000/manga-info/${comicId}")
    }
}