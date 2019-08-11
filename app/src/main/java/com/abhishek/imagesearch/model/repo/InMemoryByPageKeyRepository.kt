package com.abhishek.imagesearch.model.repo

import androidx.annotation.MainThread
import androidx.lifecycle.Transformations.switchMap
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.abhishek.imagesearch.api.Item
import com.abhishek.imagesearch.api.SearchService
import com.abhishek.imagesearch.ex.PAGE_SIZE
import com.abhishek.imagesearch.model.SearchResultsDataSourceFactory
import com.abhishek.imagesearch.model.helper.Listing
import java.util.concurrent.Executor

/**
 * Repository implementation that returns a [Listing] that loads data directly from network
 */
class InMemoryByPageKeyRepository(
    private val searchService: SearchService,
    private val networkExecutor: Executor
) : SearchResultsRepository {

    @MainThread
    override fun searchImages(searchQuery: String): Listing<Item> {

        val factory = searchResultsDataSourceFactory(searchQuery)

        val config = pagedListConfig(PAGE_SIZE)

        val livePagedList = LivePagedListBuilder(factory, config)
            .setFetchExecutor(networkExecutor)
            .build()

        return Listing(
            pagedList = livePagedList,
            networkState = switchMap(factory.source) { it.network },
            retry = { factory.source.value?.retryAllFailed() },
            refresh = { factory.source.value?.invalidate() },
            refreshState = switchMap(factory.source) { it.initial })
    }

    private fun searchResultsDataSourceFactory(searchQuery: String): SearchResultsDataSourceFactory {
        return SearchResultsDataSourceFactory(searchQuery, searchService, networkExecutor)
    }

    private fun pagedListConfig(pageSize: Int): PagedList.Config {
        return PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(pageSize * 2)
            .setPageSize(pageSize)
            .build()
    }
}