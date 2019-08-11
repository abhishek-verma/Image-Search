package com.abhishek.imagesearch.model

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.abhishek.imagesearch.api.Item
import com.abhishek.imagesearch.api.SearchService
import com.abhishek.imagesearch.ex.PAGE_SIZE
import java.util.concurrent.Executor

class SearchResultsDataSource(
    private val searchQuery: String,
    private val searchService: SearchService,
    private val retryExecutor: Executor
) : PageKeyedDataSource<Int, Item>() {

    var retry: (() -> Any)? = null
    val network = MutableLiveData<NetworkState>()
    val initial = MutableLiveData<NetworkState>()
    var totalResults: Int = 0

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Item>) {

        val nextPageStartIndex = PAGE_SIZE + 1

        searchService.searchAsync(
            query = searchQuery,
            startIndex = 1,
            onPrepared = {
                postInitialState(NetworkState.LOADING)
            },
            onSuccess = { responseBody ->
                val items = responseBody?.items?.filter { it.pagemap?.cseImage?.get(0)?.src != null } ?: emptyList()
                totalResults = responseBody?.searchInformation?.totalResults!!
                retry = null
                postInitialState(NetworkState.LOADED)
                callback.onResult(items, null, nextPageStartIndex)
            },
            onError = { errorMessage ->
                retry = { loadInitial(params, callback) }
                postInitialState(NetworkState.error(errorMessage))
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {

        val currentStartIndex = params.key
        val nextPageStartIndex = currentStartIndex + PAGE_SIZE

        if (currentStartIndex > totalResults) {
            postAfterState(NetworkState.LOADED)
        }


        searchService.searchAsync(
            query = searchQuery,
            startIndex = currentStartIndex,
            onPrepared = {
                postAfterState(NetworkState.LOADING)
            },
            onSuccess = { responseBody ->
                val items = responseBody?.items?.filter { it.pagemap?.cseImage?.get(0)?.src != null } ?: emptyList()
                retry = null
                callback.onResult(items, nextPageStartIndex)
                postAfterState(NetworkState.LOADED)
            },
            onError = { errorMessage ->
                retry = { loadAfter(params, callback) }
                postAfterState(NetworkState.error(errorMessage))
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        // ignored, since we only ever append to our initial load
    }

    fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let { retry ->
            retryExecutor.execute { retry() }
        }
    }

    private fun postInitialState(state: NetworkState) {
        network.postValue(state)
        initial.postValue(state)
    }

    private fun postAfterState(state: NetworkState) {
        network.postValue(state)
    }

}