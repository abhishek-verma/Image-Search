package com.abhishek.imagesearch.view.results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.abhishek.imagesearch.R
import com.abhishek.imagesearch.viewmodel.results.SearchResultsViewModel
import com.bumptech.glide.Glide

class SearchResultsFragment : Fragment() {

    companion object {
        fun newInstance(query: String) = SearchResultsFragment().apply {
            arguments = Bundle().apply {
                putString(SearchResultsActivity.EXTRA_QUERY, query)
            }
        }
    }

    private lateinit var viewModelSearch: SearchResultsViewModel
    private lateinit var recyclerView: RecyclerView
    private val glide by lazy { Glide.with(this) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.results_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelSearch = ViewModelProviders.of(this).get(SearchResultsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
