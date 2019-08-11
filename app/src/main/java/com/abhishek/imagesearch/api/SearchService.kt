package com.abhishek.imagesearch.api

import com.abhishek.imagesearch.BuildConfig
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("customsearch/v1?")
    fun fetchResults(
        @Query("q") query: String,
        @Query("start") start: Int,
        @Query("cx") cx: String = "011476162607576381860:ra4vmliv9ti",
        @Query("key") api_key: String = BuildConfig.GOOGLE_API_KEY
    ): Call<SearchResponse>

    fun searchAsync(
        query: String,
        startIndex: Int,
        onPrepared: () -> Unit,
        onSuccess: (SearchResponse?) -> Unit,
        onError: (String) -> Unit
    ) {

        val request = fetchResults(query, startIndex)
        onPrepared()
        ApiRequestHelper.asyncRequest(request, onSuccess, onError)
    }

    companion object {
        fun getService(): SearchService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(SearchService::class.java)
        }
    }
}