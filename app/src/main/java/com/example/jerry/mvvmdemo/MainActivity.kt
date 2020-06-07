package com.example.jerry.mvvmdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.jerry.mvvmdemo.ui.RepoFragment

class MainActivity : AppCompatActivity() {

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
