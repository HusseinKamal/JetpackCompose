package com.hussein.jetpackcompose.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface DaoPerson {
    @Query("SELECT * from person")
    fun readAll():List<Person>
}