package com.abhishek.imagesearch.view.results

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abhishek.imagesearch.R

class ResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.results_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ResultsFragment.newInstance())
                .commitNow()
        }
    }

}
