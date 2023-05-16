package com.hussein.jetpackcompose.pagingapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hussein.jetpackcompose.pagingapp.data.local.dao.UnsplashImageDao
import com.hussein.jetpackcompose.pagingapp.data.local.dao.UnsplashRemoteKeysDao
import com.hussein.jetpackcompose.pagingapp.model.UnsplashImage
import com.hussein.jetpackcompose.pagingapp.model.UnsplashRemoteKey

@Database(entities = [UnsplashImage::class, UnsplashRemoteKey::class], version = 1, exportSchema = false)
abstract class UnsplashDatabase :RoomDatabase() {
    abstract fun unsplashImageDao(): UnsplashImageDao
    abstract fun unsplashRemoteKeyDao(): UnsplashRemoteKeysDao

}