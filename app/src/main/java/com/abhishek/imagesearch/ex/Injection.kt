package com.abhishek.imagesearch.ex

import androidx.lifecycle.ViewModelProvider
import com.abhishek.imagesearch.api.SearchEndpoint
import com.abhishek.imagesearch.api.SearchService
import com.abhishek.imagesearch.model.repo.InMemoryByPageKeyRepository
import com.abhishek.imagesearch.model.repo.SearchResultsRepository
import com.abhishek.imagesearch.view.ViewModelFactory
import java.util.concurrent.Executors

object Injection {

    // thread pool used for network requests
    @Suppress("PrivatePropertyName")
    private val NETWORK_IO = Executors.newFixedThreadPool(5)

    private fun provideSearchRepository(): SearchResultsRepository {
        return InMemoryByPageKeyRepository(provideSearchService(), NETWORK_IO)
    }

    private fun provideSearchService(): SearchService {
        return SearchService(SearchEndpoint.getService())
    }


    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelFactory(provideSearchRepository())
    }
}