package com.hussein.jetpackcompose.pagingapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hussein.jetpackcompose.pagingapp.model.UnsplashRemoteKey

@Dao
interface UnsplashRemoteKeysDao {

    @Query("SELECT * FROM unsplash_remote_keys_table WHERE id=:id")
    suspend fun getRemoteKeys(id : String): UnsplashRemoteKey

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRemoteKeys(remoteKeys : List<UnsplashRemoteKey>)

    @Query("DELETE FROM unsplash_remote_keys_table")
    suspend fun deleteKeys()

}