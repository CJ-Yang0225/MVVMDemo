package com.example.jerry.mvvmdemo.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.jerry.mvvmdemo.databinding.FragmentRepoBinding
import com.example.jerry.mvvmdemo.viewmodel.GithubViewModelFactory
import kotlinx.android.synthetic.main.fragment_repo.*


private const val TAG = "Repo"


/**
 * A simple [Fragment] subclass.
 * Use the [RepoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RepoFragment : Fragment() {
    private lateinit var binding: FragmentRepoBinding
    val repoAdapter = RepoAdapter(ArrayList())
    private val factory: GithubViewModelFactory = GithubViewModelFactory()

    private lateinit var viewModel: RepoViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
// error       viewModel = ViewModelProvider.of()


        btn_search.setOnClickListener { doSearch() }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = FragmentRepoBinding.inflate(inflater, container, false)

        return binding.root
    }

//    companion object {
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//                RepoFragment().apply {
//                    arguments = Bundle().apply {
//
//                    }
//                }
//    }

    fun View.hideKeyboard(inputMethodManager: InputMethodManager) {
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun doSearch() {
        val query: String = binding.editQuery.text.toString()
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