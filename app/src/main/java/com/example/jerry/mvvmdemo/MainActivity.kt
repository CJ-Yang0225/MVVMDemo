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
import com.example.jerry.mvvmdemo.ui.RepoAdapter
import com.example.jerry.mvvmdemo.ui.RepoFragment
import kotlinx.android.synthetic.main.fragment_repo.*

class MainActivity : AppCompatActivity() {
    private val repoAdapter = RepoAdapter(ArrayList<Repo>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tag = com.example.jerry.mvvmdemo.ui.TAG

        if (supportFragmentManager.findFragmentByTag(tag) == null) {
            val fragment = RepoFragment().newInstance()
            supportFragmentManager.inTransaction {
                add(R.id.container, fragment)
            }
        }
    }

    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }
}
