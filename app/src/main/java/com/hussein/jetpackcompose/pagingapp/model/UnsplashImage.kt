package com.hussein.jetpackcompose.pagingapp.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.hussein.jetpackcompose.pagingapp.util.Constant.UNSPLASH_IMAGE_TABLE
import kotlinx.serialization.Contextual
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = UNSPLASH_IMAGE_TABLE)
data class UnsplashImage (
    @PrimaryKey(autoGenerate = false)
    val id:String,
    @Embedded
    @SerialName("urls")
    val url: Urls,
    val likes:Int,
    @Embedded
    val user: User,
)
//@Embedded --> Marks a field of an Entity or POJO to allow nested fields (i.e. fields of the annotated field's class) to be referenced directly in the SQL queries.
//If the container is an Entity, these sub fields will be columns in the Entity's database table.