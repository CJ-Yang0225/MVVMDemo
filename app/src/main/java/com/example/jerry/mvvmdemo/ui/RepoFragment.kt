package com.example.jerry.mvvmdemo.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jerry.mvvmdemo.data.model.Repo
import com.example.jerry.mvvmdemo.databinding.FragmentRepoBinding
import com.example.jerry.mvvmdemo.ui.RepoAdapter.OnItemClickListener
import com.example.jerry.mvvmdemo.viewmodel.GithubViewModelFactory
import kotlinx.android.synthetic.main.fragment_repo.*


const val TAG = "Repo"


/**
 * A simple [Fragment] subclass.
 * Use the [RepoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RepoFragment : Fragment() {
    private lateinit var binding: FragmentRepoBinding
    private val repoAdapter = RepoAdapter(ArrayList<Repo>())

    private val factory: GithubViewModelFactory = GithubViewModelFactory()
    private lateinit var viewModel: RepoViewModel

    internal fun newInstance(): RepoFragment {
        return RepoFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentRepoBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, factory).get(RepoViewModel::class.java)

        // Elegant way to achieve (Android KTX)
//        val viewModel: RepoViewModel by viewModels { factory }
        viewModel.getRepos().observe(viewLifecycleOwner, Observer<List<Repo>> { repos ->
            repoAdapter.swapItems(repos)
        })

        binding.btnSearch.setOnClickListener { doSearch() }
        binding.editQuery.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                doSearch()
                return@OnKeyListener true
            }
            false
        })

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = repoAdapter

        repoAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                Toast.makeText(context, "Clicked No.$position item" ,Toast.LENGTH_SHORT).show()
            }

            override fun onItemLongClick(view: View, position: Int) {
                TODO("Not yet implemented")
            }
        })
    }


//    fun View.hideKeyboard(inputMethodManager: InputMethodManager) {
//        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
//    }

    private fun doSearch() {
        val query: String = binding.editQuery.text.toString()
        Log.d(TAG, "doSearch: $query")
        if (query.isEmpty()) {
            repoAdapter.clearItems()
            return
        }
        viewModel.searchRepo(query)

        // 關閉軟鍵盤
        val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }
}