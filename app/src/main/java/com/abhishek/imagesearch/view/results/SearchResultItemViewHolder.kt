package com.abhishek.imagesearch.view.results

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abhishek.imagesearch.R
import com.abhishek.imagesearch.api.Item
import com.bumptech.glide.Glide

class SearchResultItemViewHolder(
    view: View,
    private val glide: Glide
) : RecyclerView.ViewHolder(view) {

    private val title: TextView = view.findViewById(R.id.titleTxtV)
    //    private val thumbnail: ImageView = view.findViewById(R.id.thumbnail)
    private var item: Item? = null

    // TODO remove if unused
//    init {
//        view.setOnClickListener {
//            item?.title?.let { url ->
//                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//                view.context.startActivity(intent)
//            }
//        }
//    }

    fun bind(item: Item?) {
        this.item = item
        title.text = item?.title ?: "loading"

//        if (item?.avatarUrl?.startsWith("http") == true) {
//            thumbnail.visibility = View.VISIBLE
//            glide.load(item.avatarUrl)
//                .centerCrop()
//                .placeholder(R.drawable.ic_placeholder)
//                .into(thumbnail)
//        } else {
//            thumbnail.visibility = View.GONE
//            glide.clear(thumbnail)
//        }
    }

    companion object {
        fun create(parent: ViewGroup, glide: Glide): SearchResultItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.search_result_list_item, parent, false)
            return SearchResultItemViewHolder(view, glide)
        }
    }
}