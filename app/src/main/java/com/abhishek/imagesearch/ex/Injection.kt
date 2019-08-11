package com.abhishek.imagesearch.ex

import androidx.lifecycle.ViewModelProvider
import com.abhishek.imagesearch.api.SearchService
import com.abhishek.imagesearch.model.repo.InMemoryByPageKeyRepository
import com.abhishek.imagesearch.model.repo.SearchResultsRepository
import com.abhishek.imagesearch.view.ViewModelFactory
import java.util.concurrent.Executors

object Injection {

    // thread pool used for network requests
    @Suppress("PrivatePropertyName")
    private val NETWORK_IO = Executors.newFixedThreadPool(5)

    /**
     * Creates an instance of [GithubRepository] based on the [GithubApiService]
     */
    private fun provideSearchRepository(): SearchResultsRepository {
        return InMemoryByPageKeyRepository(provideSearchService(), NETWORK_IO)
    }

    /**
     * Creates an instance of [GithubApiService] based on the [GithubApi]
     */
    private fun provideSearchService(): SearchService {
        return SearchService.getService()
    }

    /**
     * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
     * [ViewModel] objects.
     */
    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelFactory(provideSearchRepository())
    }
}