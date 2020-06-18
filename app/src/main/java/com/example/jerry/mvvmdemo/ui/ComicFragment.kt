package com.example.jerry.mvvmdemo.ui

import android.app.Activity
import android.content.Intent
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
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.jerry.mvvmdemo.data.model.Comic
import com.example.jerry.mvvmdemo.databinding.FragmentComicBinding
import com.example.jerry.mvvmdemo.viewmodel.ComicViewModelFactory
import kotlinx.android.synthetic.main.fragment_repo.*


const val COMIC = "Comic"

class ComicFragment : Fragment() {
    private lateinit var binding: FragmentComicBinding
    private val comicAdapter = ComicAdapter(ArrayList<Comic>())
    private val factory: ComicViewModelFactory = ComicViewModelFactory()
    private lateinit var viewModel: ComicViewModel
    val comicActivity = ComicActivity()

    internal fun newInstance(): ComicFragment {
        return ComicFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentComicBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this, factory).get(ComicViewModel::class.java)

        // Elegant way to achieve (Android KTX)
//        val viewModel: RepoViewModel by viewModels { factory }
        viewModel.getComics().observe(viewLifecycleOwner, Observer<List<Comic>> { comics ->
            comicAdapter.swapItems(comics)
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

        recyclerView.adapter = comicAdapter

        comicAdapter.setOnItemClickListener(object : ComicAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                Toast.makeText(context, "Clicked No.$position item" , Toast.LENGTH_SHORT).show()
                viewModel.getComics().observe(viewLifecycleOwner, Observer<List<Comic>> { comics ->
                    Log.d(javaClass.toString(), "comicId: ${comics[position].id}")
                    comicId = comics[position].id
                })
                startActivity(Intent(context, ComicActivity::class.java))
            }

            override fun onItemLongClick(view: View, position: Int) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun doSearch() {
        val query: String = binding.editQuery.text.toString()
        Log.d(javaClass.toString(), "doSearch: $query")
        if (query.isEmpty()) {
            comicAdapter.clearItems()
            return
        }
        viewModel.searchComic(query)

        // 關閉軟鍵盤
        val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
//        imm?.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
        imm?.hideSoftInputFromWindow(view?.windowToken, 0);

    }
}