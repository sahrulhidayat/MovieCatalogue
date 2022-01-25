package com.sahrul.moviecatalogue.utils

import com.sahrul.moviecatalogue.data.source.remote.response.GenresMovie
import com.sahrul.moviecatalogue.data.source.remote.response.GenresTvShow

object ResponseFormatter {
    fun movieCategoryFormatter(genres: List<GenresMovie>): String {
        val category = ArrayList<String>()
        for (item in genres) {
            category.add(item.name)
        }

        return category.joinToString()
    }

    fun tvShowCategoryFormatter(genres: List<GenresTvShow>): String {
        val category = ArrayList<String>()
        for (item in genres) {
            category.add(item.name)
        }

        return category.joinToString()
    }

    fun durationFormatter(runtime: Int): String {
        val hours = runtime / 60
        val minutes = runtime % 60

        return "${hours}h ${minutes}m"
    }
}