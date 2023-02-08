package com.bdjobsniloy.movieapp.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("poster_path")
    val poster_path: String?,
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("release_date")
    val release_date: String,
    @SerializedName("genres")
    val genres: List<Genres>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("budget")
    val budget: Int,
    @SerializedName("imdb_id")
    val imdb_id: String,
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
    val vote_count: Int,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("revenue")
    val revenue: Long,
    @SerializedName("video")
    val video: Boolean,
    @SerializedName("vote_average")
    val vote_average: Number,
    @SerializedName("production_companies")
    val production_companies: List<ProductionCompanies>,
    @SerializedName("production_countries")
    val production_countries: List<ProductionCountries>,
    @SerializedName("spoken_languages")
    val spoken_languages: List<SpokenLanguages>,
){
    data class Genres (
        @SerializedName("id")
        var id : Int,
        @SerializedName("name")
        var name : String
    )

    data class ProductionCompanies (
        @SerializedName("id")
        var id : Int,
        @SerializedName("logo_path")
        var logo_path : String,
        @SerializedName("name")
        var name : String,
        @SerializedName("origin_country")
        var origin_country : String

    )

    data class ProductionCountries (
        @SerializedName("iso_3166_1")
        var iso_3166_1 : String,
        @SerializedName("name")
        var name : String
    )

    data class SpokenLanguages (
        @SerializedName("iso_639_1")
        var iso_639_1 : String,
        @SerializedName("name")
        var name : String
    )
}
