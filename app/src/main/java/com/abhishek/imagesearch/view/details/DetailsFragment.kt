package com.abhishek.imagesearch.view.details

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.abhishek.imagesearch.R
import com.abhishek.imagesearch.ex.log
import com.abhishek.imagesearch.view.results.SearchResultsActivity
import kotlinx.android.synthetic.main.search_fragment.*

class DetailsFragment : Fragment() {

    companion object {
        fun newInstance() = DetailsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchBtn.setOnClickListener {
            val intent = Intent(context, SearchResultsActivity::class.java)
            intent.putExtra(SearchResultsActivity.EXTRA_QUERY, searchInputEdTxtV.text.toString())
            log(searchInputEdTxtV.text.toString(), 'd')
            startActivity(intent)
        }

    }
}
