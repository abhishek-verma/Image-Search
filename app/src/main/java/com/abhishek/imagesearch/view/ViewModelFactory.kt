package com.abhishek.imagesearch.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abhishek.imagesearch.model.repo.SearchResultsRepository
import com.abhishek.imagesearch.viewmodel.results.SearchResultsViewModel

class ViewModelFactory(private val repository: SearchResultsRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchResultsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchResultsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}