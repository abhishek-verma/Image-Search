package com.abhishek.imagesearch.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SearchResponse(
    @SerializedName("items") val items: List<Item>,
    @SerializedName("searchInformation") val searchInformation: SearchInformation
)

data class SearchInformation(
    @SerializedName("totalResults") val totalResults: Int
)

data class Item(
    @SerializedName("title") val title: String,
    @SerializedName("formattedUrl") val link: String,
    @SerializedName("snippet") val snippet: String,
    @SerializedName("pagemap") val pagemap: PageMap?
) : Serializable

data class PageMap(
    @SerializedName("cse_thumbnail") val cseThumbnail: List<CseThumbnail>?,
    @SerializedName("metatags") val metatags: List<MetaTag>?,
    @SerializedName("cse_image") val cseImage: List<CseImage>?
) : Serializable

data class CseThumbnail(
    @SerializedName("src") val src: String?
) : Serializable

data class CseImage(
    @SerializedName("src") val src: String?
) : Serializable

data class MetaTag(
    @SerializedName("og:title") val ogTitle: String?
) : Serializable
