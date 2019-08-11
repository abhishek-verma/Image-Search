package com.abhishek.imagesearch.view.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abhishek.imagesearch.R
import com.abhishek.imagesearch.view.search.SearchFragment

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, SearchFragment.newInstance())
                .commitNow()
        }
    }

}
