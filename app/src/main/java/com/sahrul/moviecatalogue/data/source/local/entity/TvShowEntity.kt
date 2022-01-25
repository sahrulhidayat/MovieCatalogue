package com.sahrul.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_show_entities")
data class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "release")
    val release: String,

    @ColumnInfo(name = "ratings")
    val ratings: Double,

    @Nullable
    @ColumnInfo(name = "category")
    val category: String? = null,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false,
)