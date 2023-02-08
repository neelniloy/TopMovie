package com.bdjobsniloy.movieapp.network

import com.bdjobsniloy.movieapp.model.Movie
import com.bdjobsniloy.movieapp.model.NowShowing
import com.bdjobsniloy.movieapp.model.Popular
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

const val base_url = "https://api.themoviedb.org/3/movie/"
const val movie_api_key = "2bd6abca42954ca4fce07eedac57c3d1"

val retrofit = Retrofit.Builder()
    .baseUrl(base_url)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface MovieApi {
    @GET()
    suspend fun getNowShowing(@Url endUrl: String): NowShowing

    @GET()
    suspend fun getPopularMovie(@Url endUrl: String): Popular

    @GET()
    suspend fun getMovie(@Url endUrl: String): Movie

}

object NetworkService {

    val movieApi: MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }
}