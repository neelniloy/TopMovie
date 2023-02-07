package com.bdjobsniloy.movieapp.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bdjobsniloy.movieapp.model.NowShowing
import com.bdjobsniloy.movieapp.model.Popular
import com.bdjobsniloy.movieapp.repos.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel: ViewModel() {
    val repository = MovieRepository()
    val nowShowingLD: MutableLiveData<NowShowing> = MutableLiveData()
    val popularLD: MutableLiveData<Popular> = MutableLiveData()

    fun fetchNowShowing(page:Int){

        viewModelScope.launch {
            try {
                nowShowingLD.value = repository.fetchNowShowing(page)
                Log.e("MovieViewModel", repository.fetchNowShowing(page).toString())
            }catch (e: Exception) {
                Log.e("MovieViewModel", e.localizedMessage)
            }
        }

    }

    fun fetchPopularMovie(page:Int){

        viewModelScope.launch {
            try {
                popularLD.value = repository.fetchPopularMovie(page)
                Log.e("MovieViewModel", repository.fetchPopularMovie(page).toString())
            }catch (e: Exception) {
                Log.e("MovieViewModel", e.localizedMessage)
            }
        }

    }

}