package com.abhishek.imagesearch.api

class SearchService(
    private val searchEndpoint: SearchEndpoint
) {


    fun searchAsync(
        query: String,
        startIndex: Int,
        onPrepared: () -> Unit,
        onSuccess: (SearchResponse?) -> Unit,
        onError: (String) -> Unit
    ) {

        val request = searchEndpoint.fetchResults(query, startIndex)
        onPrepared()
        ApiRequestHelper.asyncRequest(request, onSuccess, onError)
    }


}