package com.example.marvelapp1

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MarvelApiClient {
    private const val BASE_URL = "https://gateway.marvel.com/v1/public/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val marvelApiService: MarvelApiService = retrofit.create(MarvelApiService::class.java)
}
