package com.sahrul.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowDetailsResponse(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("poster_path")
    val posterPath: String,

    @field:SerializedName("name")
    val title: String,

    @field:SerializedName("overview")
    val overview: String,

    @field:SerializedName("first_air_date")
    val releaseDate: String,

    @field:SerializedName("vote_average")
    val voteAverage: Double,

    @field:SerializedName("genres")
    val genres: List<GenresTvShow>,
)

data class GenresTvShow(

    @field:SerializedName("name")
    val name: String

)
