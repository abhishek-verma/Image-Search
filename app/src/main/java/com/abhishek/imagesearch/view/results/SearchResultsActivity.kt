package com.abhishek.imagesearch.view.results

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abhishek.imagesearch.R

class SearchResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.results_activity)
        if (savedInstanceState == null) {
            val strUser: String = intent.getStringExtra(EXTRA_QUERY)!!
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SearchResultsFragment.newInstance(strUser))
                .commitNow()
        }
    }

    companion object {
        const val EXTRA_QUERY: String = "query"
    }

}
