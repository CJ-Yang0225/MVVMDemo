package com.example.jerry.mvvmdemo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jerry.mvvmdemo.data.model.Repo
import com.example.jerry.mvvmdemo.ui.ComicFragment
import com.example.jerry.mvvmdemo.ui.RepoAdapter
import com.example.jerry.mvvmdemo.ui.RepoFragment
import kotlinx.android.synthetic.main.fragment_repo.*

class MainActivity : AppCompatActivity() {
    private val repoAdapter = RepoAdapter(ArrayList<Repo>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tag = com.example.jerry.mvvmdemo.ui.COMIC

        // 轉換至 Fragment
        if (supportFragmentManager.findFragmentByTag(tag) == null) {
            val fragment = ComicFragment().newInstance()
            supportFragmentManager.inTransaction {
                add(R.id.container, fragment)
            }
        }
    }

    // 轉換 Fragment 的 Kotlin 擴充套件(Extension)
    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }
}
