package com.example.marvelapp1




import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApiService {
    @GET("comics")
    fun getComics(
        @Query("apikey") apiKey: String,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String
    ): Call<ComicsResponse>

    @GET("comics")
    fun searchComics(
        @Query("apikey") apiKey: String,
        @Query("ts") timestamp: String,
        @Query("hash") hash: String,
        @Query("titleStartsWith") query: String
    ): Call<ComicsResponse>
}