package com.hussein.jetpackcompose.pagingapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hussein.jetpackcompose.pagingapp.util.Constant

@Entity(tableName = Constant.UNSPLASH_REMOTE_KEYS_TABLE)
data class UnsplashRemoteKey (
    @PrimaryKey(autoGenerate = false)
    val id:String,
    val prevPage:Int?,
    val nextPage:Int?,
)