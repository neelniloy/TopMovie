package com.bdjobsniloy.movieapp.model

import com.google.gson.annotations.SerializedName

data class Popular(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Movie>,
    @SerializedName("total_pages")
    val total_pages: String,
    @SerializedName("total_results")
    val total_results: String,
){
    data class Movie(
        @SerializedName("poster_path")
        val poster_path: String?,
        @SerializedName("adult")
        val adult: Boolean,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("release_date")
        val release_date: String,
        @SerializedName("genre_ids")
        val genre_ids: List<Int>,
        @SerializedName("id")
        val id: Long,
        @SerializedName("original_title")
        val original_title: String,
        @SerializedName("original_language")
        val original_language: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("backdrop_path")
        val backdrop_path: String?,
        @SerializedName("popularity")
        val popularity: Number,
        @SerializedName("vote_count")
        val vote_count: String,
        @SerializedName("video")
        val video: Boolean,
        @SerializedName("vote_average")
        val vote_average: Number
    )
}
