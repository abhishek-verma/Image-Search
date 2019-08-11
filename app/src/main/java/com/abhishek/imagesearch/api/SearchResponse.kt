package com.abhishek.imagesearch.api

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("items") val items: List<Item>,
    @SerializedName("searchInformation") val searchInformation: SearchInformation
)

data class SearchInformation(
    @SerializedName("totalResults") val totalResults: Int
)

data class Item(
    @SerializedName("title") val title: String,
    @SerializedName("link") val link: String,
    @SerializedName("snippet") val snippet: String,
    @SerializedName("pagemap") val pagemap: PageMap?
)

data class PageMap(
    @SerializedName("cse_thumbnail") val cseThumbnail: List<CseThumbnail>?,
    @SerializedName("metatags") val metatags: List<MetaTag>?,
    @SerializedName("cse_image") val cseImage: List<CseImage>?
)

data class CseThumbnail(
    @SerializedName("src") val src: String?
)

data class CseImage(
    @SerializedName("src") val src: String?
)

data class MetaTag(
    @SerializedName("og:title") val ogTitle: String?
)
