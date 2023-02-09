package com.bdjobsniloy.movieapp.repos

import com.bdjobsniloy.movieapp.entities.Genre
import com.bdjobsniloy.movieapp.model.GenreModel
import com.bdjobsniloy.movieapp.model.Movie
import com.bdjobsniloy.movieapp.model.NowShowing
import com.bdjobsniloy.movieapp.model.Popular
import com.bdjobsniloy.movieapp.network.NetworkService
import com.bdjobsniloy.movieapp.network.movie_api_key

class MovieRepository {
    suspend fun fetchNowShowing(page: Int): NowShowing {
        val endUrl = "movie/now_playing?api_key=${movie_api_key}&language=en-US&page=${page}"
        return NetworkService.movieApi.getNowShowing(endUrl)
    }

    suspend fun fetchPopularMovie(page: Int): Popular {
        val endUrl = "movie/popular?api_key=${movie_api_key}&language=en-US&page=${page}"
        return NetworkService.movieApi.getPopularMovie(endUrl)
    }

    suspend fun fetchMovie(movie_id: Int): Movie {
        val endUrl = "movie/$movie_id?api_key=${movie_api_key}&language=en-US"
        return NetworkService.movieApi.getMovie(endUrl)
    }

    suspend fun fetchGenre(): GenreModel {
        val endUrl = "genre/movie/list?api_key=${movie_api_key}&language=en-US"
        return NetworkService.movieApi.getGenreList(endUrl)
    }
}