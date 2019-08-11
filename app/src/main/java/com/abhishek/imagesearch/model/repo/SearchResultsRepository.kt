package com.abhishek.imagesearch.model.repo

import com.abhishek.imagesearch.api.Item
import com.abhishek.imagesearch.model.helper.Listing

interface SearchResultsRepository {
    fun searchImages(searchQuery: String): Listing<Item>
}