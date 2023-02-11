package com.bdjobsniloy.movieapp.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bdjobsniloy.movieapp.entities.Genre

@Dao
interface GenreDao {

    @Insert
    suspend fun insertGenre(genre: Genre) : Long

    @Query("select * from tbl_genre where genre_id = :genre_id")
    suspend fun getGenreById(genre_id: Int) : Genre?

    @Query("select * from tbl_genre")
    suspend fun getGenreList() : List<Genre>

    @Query("select * from tbl_genre")
    suspend fun getGenre() : Genre

}