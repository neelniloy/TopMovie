package com.bdjobsniloy.movieapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_genre")
data class Genre(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "genre_id")
    var genre_id: Int = 0,
    var id: Int = 0,
    var name: String,
)