package com.sahrul.moviecatalogue.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowsResponse(

    @field:SerializedName("results")
    val results: List<ResultsTvShow>
)

data class ResultsTvShow(

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
)
