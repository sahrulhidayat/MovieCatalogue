package com.sahrul.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("release_date")
    val releaseDate: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("genres")
    val genres: List<GenresMovie>,

    @field:SerializedName("runtime")
    val runtime: Int
)

data class GenresMovie(

    @field:SerializedName("name")
    val name: String

)
