package com.abhishek.imagesearch.view.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.abhishek.imagesearch.R
import com.abhishek.imagesearch.api.Item

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        if (savedInstanceState == null) {
            val item: Item = (intent.getSerializableExtra(EXTRA_ITEM) as Item?)!!
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DetailFragment.newInstance(item))
                .commitNow()
        }
    }

    companion object {
        const val EXTRA_ITEM: String = "item"
    }
}
