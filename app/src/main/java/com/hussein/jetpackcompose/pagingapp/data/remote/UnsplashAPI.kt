package com.hussein.jetpackcompose.pagingapp.data.remote

import com.hussein.jetpackcompose.BuildConfig
import com.hussein.jetpackcompose.pagingapp.model.SearchResult
import com.hussein.jetpackcompose.pagingapp.model.UnsplashImage
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashAPI{

    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/photos")
    suspend fun getAllImages(
        @Query("page") page:Int,
        @Query("per_page") perPage:Int):List<UnsplashImage>

    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/search/photos")
    suspend fun searchImages(
        @Query("query") page:String,
        @Query("per_page") perPage:Int): SearchResult

}