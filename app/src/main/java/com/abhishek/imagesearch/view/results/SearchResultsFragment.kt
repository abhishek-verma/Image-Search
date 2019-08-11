package com.abhishek.imagesearch.view.results

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import com.abhishek.imagesearch.R
import com.abhishek.imagesearch.api.Item
import com.abhishek.imagesearch.ex.Injection
import com.abhishek.imagesearch.viewmodel.results.SearchResultsViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.results_fragment.*

class SearchResultsFragment : Fragment() {

    companion object {
        fun newInstance(query: String) = SearchResultsFragment().apply {
            arguments = Bundle().apply {
                putString(SearchResultsActivity.EXTRA_QUERY, query)
            }
        }
    }

    private lateinit var viewModelSearch: SearchResultsViewModel
    private val glide by lazy { Glide.with(this) }
    private lateinit var query: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.results_fragment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getString(SearchResultsActivity.EXTRA_QUERY)?.let {
            query = it
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViewModel()
        initAdapter()
        viewModelSearch.showSearchResults(query)
    }


    private fun initAdapter() {
        val adapter = SearchResultsAdapter(glide) {
            viewModelSearch.retry()
        }
        recyclerView.adapter = adapter
        viewModelSearch.items.observe(this, Observer<PagedList<Item>> {
            adapter.submitList(it)
        })
        viewModelSearch.networkState.observe(this, Observer {
            adapter.setNetworkState(it)
        })
    }

    private fun initViewModel() {
        viewModelSearch = ViewModelProviders.of(
            this,
            Injection.provideViewModelFactory()
        ).get(SearchResultsViewModel::class.java)
    }

}
