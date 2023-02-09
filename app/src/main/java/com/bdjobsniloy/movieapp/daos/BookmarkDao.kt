package com.bdjobsniloy.movieapp.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bdjobsniloy.movieapp.entities.Bookmark

@Dao
interface BookmarkDao {
    @Insert
    suspend fun insertBookmark(bookmark: Bookmark) : Long

    @Delete
    suspend fun deleteBookmark(bookmark: Bookmark)

    @Query("select * from tbl_bookmark where id = :id")
    suspend fun getMovieById(id: Int) : Bookmark?

    @Query("delete from tbl_bookmark where id = :movieId")
    suspend fun delete(movieId: Int)
}