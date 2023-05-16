package com.hussein.jetpackcompose.pagingapp.data.di

import android.content.Context
import androidx.room.Room
import com.hussein.jetpackcompose.pagingapp.data.local.UnsplashDatabase
import com.hussein.jetpackcompose.pagingapp.util.Constant.UNSPLASH_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) : UnsplashDatabase {
        return Room.databaseBuilder(context, UnsplashDatabase::class.java,UNSPLASH_DATABASE).build()

    }
}