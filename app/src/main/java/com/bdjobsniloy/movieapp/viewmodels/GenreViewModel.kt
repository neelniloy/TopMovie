package com.bdjobsniloy.movieapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bdjobsniloy.movieapp.db.MovieDB
import com.bdjobsniloy.movieapp.entities.Bookmark
import com.bdjobsniloy.movieapp.entities.Genre
import com.bdjobsniloy.movieapp.model.GenreModel
import kotlinx.coroutines.launch

class GenreViewModel(application: Application): AndroidViewModel(application) {
    private val genreDao = MovieDB.getDB(application).genreDao()
    var genreModel: Genre? = null
    val genresLD: MutableLiveData<String> = MutableLiveData()
    val genreExitsLD: MutableLiveData<Boolean> = MutableLiveData()

    fun addGenre(genre: Genre) {
        viewModelScope.launch {
            genreModel = genreDao.getGenreById(genre.id)
            if (genreModel != null) {

            }else {
                val rowid = genreDao.insertGenre(genre)
                genreModel = genre.apply {
                    genre_id = rowid.toInt()
                }
            }
        }
    }


    fun getGenreName(id:Int): MutableLiveData<String> {
        viewModelScope.launch {
            genreModel = genreDao.getGenreById(id)
            if (genreModel != null) {
                genresLD.postValue(genreDao.getGenreById(id)!!.name)
            }
        }
        return genresLD
    }

    fun genreExits() {
        viewModelScope.launch {
            genreModel = genreDao.getGenre()
            genreExitsLD.value = genreModel != null
        }
    }
}