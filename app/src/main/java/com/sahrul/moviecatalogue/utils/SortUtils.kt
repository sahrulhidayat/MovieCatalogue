package com.sahrul.moviecatalogue.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val NEWEST = "Newest"
    const val OLDEST = "Oldest"
    const val RATINGS = "Rantings"

    fun getSortedQueryMovies(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM movie_entities ")
        when (filter) {
            NEWEST -> {
                simpleQuery.append("ORDER BY release DESC")
            }
            OLDEST -> {
                simpleQuery.append("ORDER BY release ASC")
            }
            RATINGS -> {
                simpleQuery.append("ORDER BY ratings DESC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getSortedQueryTvShows(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM tv_show_entities ")
        when (filter) {
            NEWEST -> {
                simpleQuery.append("ORDER BY release DESC")
            }
            OLDEST -> {
                simpleQuery.append("ORDER BY release ASC")
            }
            RATINGS -> {
                simpleQuery.append("ORDER BY ratings DESC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}