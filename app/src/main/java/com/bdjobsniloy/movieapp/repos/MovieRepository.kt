package com.bdjobsniloy.movieapp.repos

import com.bdjobsniloy.movieapp.model.NowShowing
import com.bdjobsniloy.movieapp.model.Popular
import com.bdjobsniloy.movieapp.network.NetworkService
import com.bdjobsniloy.movieapp.network.movie_api_key

class MovieRepository {
    suspend fun fetchNowShowing(page: Int): NowShowing {
        val endUrl = "now_playing?api_key=${movie_api_key}&language=en-US&page=${page}"
        return NetworkService.movieApi.getNowShowing(endUrl)
    }

    suspend fun fetchPopularMovie(page: Int): Popular {
        val endUrl = "popular?api_key=${movie_api_key}&language=en-US&page=${page}"
        return NetworkService.movieApi.getPopularMovie(endUrl)
    }
}