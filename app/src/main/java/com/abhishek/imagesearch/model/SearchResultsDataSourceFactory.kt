package com.abhishek.imagesearch.model

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.abhishek.imagesearch.api.Item
import com.abhishek.imagesearch.api.SearchService
import java.util.concurrent.Executor

class SearchResultsDataSourceFactory(
    private val searchQuery: String,
    private val searchService: SearchService,
    private val retryExecutor: Executor
) : DataSource.Factory<Int, Item>() {

    val source = MutableLiveData<SearchResultsDataSource>()

    override fun create(): DataSource<Int, Item> {
        val source = SearchResultsDataSource(searchQuery, searchService, retryExecutor)
        this.source.postValue(source)
        return source
    }
}