package com.bdjobsniloy.movieapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bdjobsniloy.movieapp.db.MovieDB
import com.bdjobsniloy.movieapp.entities.Bookmark
import com.bdjobsniloy.movieapp.model.Movie
import com.bdjobsniloy.movieapp.model.Popular
import kotlinx.coroutines.launch

class BookmarkViewModel(application: Application):AndroidViewModel(application) {
    private val bookmarkDao = MovieDB.getDB(application).bookmarkDao()
    var bookmarkModel: Bookmark? = null
    val bookmarkExitsLD: MutableLiveData<Boolean> = MutableLiveData()
    val movieLD: MutableLiveData<List<Bookmark>> = MutableLiveData()

    fun addToBookmark(bookmark: Bookmark, callback:(String,Int) -> Unit) {
        viewModelScope.launch {
            bookmarkModel = bookmarkDao.getMovieById(bookmark.id)
            if (bookmarkModel != null) {
                callback("Movie already exists",-1)
            }else {
                val rowid = bookmarkDao.insertBookmark(bookmark)
                bookmarkModel = bookmark.apply {
                    bookmark_id = rowid.toInt()
                }
                callback("Success", rowid.toInt())
            }
        }
    }

    fun bookmarkExits(id: Int) {
        viewModelScope.launch {
            bookmarkModel = bookmarkDao.getMovieById(id)
            bookmarkExitsLD.value = bookmarkModel != null
        }
    }

    fun removeMovie(id: Int,callback:(String) -> Unit) {
        viewModelScope.launch {
            bookmarkDao.delete(id)
            callback("Success")
        }
    }

    fun getMovieList(): MutableLiveData<List<Bookmark>>{
        viewModelScope.launch {
            movieLD.postValue(bookmarkDao.getMovieList())
        }
        return movieLD
    }
}