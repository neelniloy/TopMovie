package com.bdjobsniloy.movieapp.network

import com.bdjobsniloy.movieapp.model.NowShowing
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

const val base_url = "https://api.themoviedb.org/3/movie/"
const val movie_api_key = "19c1c2d504f8ac3c45453893ebb0e54d"

val retrofit = Retrofit.Builder()
    .baseUrl(base_url)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface MovieApi {
    @GET()
    suspend fun getNowShowing(@Url endUrl: String): NowShowing

}

object NetworkService {

    val movieApi: MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }
}