package com.abhishek.imagesearch.api

import com.abhishek.imagesearch.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchEndpoint {

    @GET("customsearch/v1?")
    fun fetchResults(
        @Query("q") query: String,
        @Query("start") start: Int,
        @Query("cx") cx: String = "011476162607576381860:ra4vmliv9ti",
        @Query("key") api_key: String = BuildConfig.GOOGLE_API_KEY
    ): Call<SearchResponse>

    companion object {
        fun getService(): SearchEndpoint {

            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(SearchEndpoint::class.java)
        }
    }
}