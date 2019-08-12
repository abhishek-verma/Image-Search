package com.abhishek.imagesearch.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.abhishek.imagesearch.R
import com.abhishek.imagesearch.api.Item
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.android.synthetic.main.search_result_list_item.descTxtV
import kotlinx.android.synthetic.main.search_result_list_item.imageView
import kotlinx.android.synthetic.main.search_result_list_item.titleTxtV

class DetailFragment : Fragment() {


    companion object {
        fun newInstance(item: Item) = DetailFragment().apply {
            arguments = Bundle().apply {
                putSerializable(DetailActivity.EXTRA_ITEM, item)
            }
        }
    }

    private lateinit var item: Item
    private val glide by lazy { Glide.with(this) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        arguments?.getSerializable(DetailActivity.EXTRA_ITEM)?.let {
            item = it as Item
        }
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        titleTxtV.text = item.title
        descTxtV.text = item.snippet
        urlTxtV.text = item.link

        glide.load(item.pagemap?.cseThumbnail?.get(0)?.src)
            .placeholder(R.drawable.ic_launcher_background)
            .centerCrop()
            .into(imageView)
    }
}
