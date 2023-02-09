package com.bdjobsniloy.movieapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bdjobsniloy.movieapp.daos.BookmarkDao
import com.bdjobsniloy.movieapp.daos.GenreDao
import com.bdjobsniloy.movieapp.entities.Bookmark
import com.bdjobsniloy.movieapp.entities.Genre

@Database(entities = [Bookmark::class,Genre::class], version = 2)
abstract class MovieDB : RoomDatabase() {
    abstract fun bookmarkDao() : BookmarkDao
    abstract fun genreDao() : GenreDao

    companion object {
        private var db: MovieDB? = null
        fun getDB(context: Context) : MovieDB {
            if (db == null) {
                db = Room.databaseBuilder(
                    context,
                    MovieDB::class.java,
                    "movie_db"
                ).build()
                return db!!
            }
            return db!!
        }
    }
}