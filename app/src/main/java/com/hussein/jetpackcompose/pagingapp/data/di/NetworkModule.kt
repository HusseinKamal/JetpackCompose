package com.hussein.jetpackcompose.pagingapp.data.di

import com.hussein.jetpackcompose.pagingapp.data.remote.UnsplashAPI
import com.hussein.jetpackcompose.pagingapp.util.Constant.BASE_URL
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient():OkHttpClient{
        return OkHttpClient().newBuilder()
            .readTimeout(15,TimeUnit.SECONDS)
            .connectTimeout(15,TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        val convertType= "application/json".toMediaType()

        val json= Json { ignoreUnknownKeys=true }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(convertType))
            .build()
    }

    @Provides
    @Singleton
    fun provideUnsplashAPI(retrofit: Retrofit) : UnsplashAPI {
        return  retrofit.create(UnsplashAPI::class.java)
    }
}