package com.bdjobsniloy.movieapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bdjobsniloy.movieapp.daos.BookmarkDao
import com.bdjobsniloy.movieapp.entities.Bookmark

@Database(entities = [Bookmark::class], version = 1)
abstract class MovieDB : RoomDatabase() {
    abstract fun bookmarkDao() : BookmarkDao

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