package com.abhishek.imagesearch.view.results

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abhishek.imagesearch.R
import com.abhishek.imagesearch.api.Item
import com.bumptech.glide.RequestManager

class SearchResultItemViewHolder(
    view: View,
    private val glide: RequestManager
) : RecyclerView.ViewHolder(view) {

    private val title: TextView = view.findViewById(R.id.titleTxtV)
    private val imageView: ImageView = view.findViewById(R.id.imageView)
    private val moreBtn: ImageButton = view.findViewById(R.id.moreBtn)
    private val desc: TextView = view.findViewById(R.id.descTxtV)
    private var item: Item? = null

    init {
        moreBtn.setOnClickListener {
            if (desc.visibility == GONE)
                desc.visibility = VISIBLE
            else
                desc.visibility = GONE
        }
    }

    fun bind(item: Item?) {
        this.item = item
        title.text = item?.title
        desc.text = item?.link


        desc.visibility = GONE

        glide.load(item?.pagemap?.cseThumbnail?.get(0)?.src)
            .placeholder(R.drawable.ic_launcher_background)
            .centerCrop()
            .into(imageView)
    }

    companion object {
        fun create(parent: ViewGroup, glide: RequestManager): SearchResultItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.search_result_list_item, parent, false)
            return SearchResultItemViewHolder(view, glide)
        }
    }
}