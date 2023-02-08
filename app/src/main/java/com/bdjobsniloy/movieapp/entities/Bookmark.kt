package com.bdjobsniloy.movieapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_bookmark")
data class Bookmark(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "bookmark_id")
    var bookmark_id: Long = 0,
    var id: Long = 0,
    var title: String,
    var rating: Float,
    var url: String,
    var genre: String,
    var runtime: Long,
)