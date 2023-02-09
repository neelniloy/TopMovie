package com.bdjobsniloy.movieapp.model

import com.google.gson.annotations.SerializedName

data class GenreModel(
    @SerializedName("genres" )
    var genres : List<Movie.Genres>
)
