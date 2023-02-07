package com.bdjobsniloy.movieapp.repos

import com.bdjobsniloy.movieapp.model.NowShowing
import com.bdjobsniloy.movieapp.network.NetworkService
import com.bdjobsniloy.movieapp.network.movie_api_key

class MovieRepository {
    suspend fun fetchNowShowing(page: Int): NowShowing {
        val endUrl = "now_playing?api_key=${movie_api_key}&language=en-US&page=${page}"
        return NetworkService.movieApi.getNowShowing(endUrl)
    }
}