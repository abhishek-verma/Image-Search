package com.abhishek.imagesearch.viewmodel.results

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import com.abhishek.imagesearch.model.repo.SearchResultsRepository

class ResultsViewModel(
    private val repository: SearchResultsRepository
) : ViewModel() {

    private val searchQuery = MutableLiveData<String>()
    private val itemResult = map(searchQuery) {
        repository.searchImages(it)
    }

    val items = switchMap(itemResult) { it.pagedList }!!
    val networkState = switchMap(itemResult) { it.networkState }!!
    val refreshState = switchMap(itemResult) { it.refreshState }!!

    fun refresh() {
        itemResult.value?.refresh?.invoke()
    }

    fun showSearchResults(searchQuery: String): Boolean {
        if (this.searchQuery.value == searchQuery) {
            return false
        }
        this.searchQuery.value = searchQuery
        return true
    }

    fun retry() {
        val listing = itemResult?.value
        listing?.retry?.invoke()
    }

    fun currentSearchQuery(): String? = searchQuery.value
}