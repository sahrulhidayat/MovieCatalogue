package com.sahrul.moviecatalogue.utils

import com.sahrul.moviecatalogue.data.source.local.entity.TvShowEntity
import com.sahrul.moviecatalogue.data.source.remote.response.GenresTvShow
import com.sahrul.moviecatalogue.data.source.remote.response.ResultsTvShow
import com.sahrul.moviecatalogue.data.source.remote.response.TvShowDetailsResponse

object DummyTvShows {

    fun generateDummyTvShows(): List<TvShowEntity> {
        val tvShow = ArrayList<TvShowEntity>()
        tvShow.add(
            TvShowEntity(
                0,
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/pqzjCxPVc9TkVgGRWeAoMmyqkZV.jpg",
                "Hawkeye",
                "Former Avenger Clint Barton has a seemingly simple mission: get back to his family for Christmas. Possible? Maybe with the help of Kate Bishop, a 22-year-old archer with dreams of becoming a superhero. The two are forced to work together when a presence from Barton’s past threatens to derail far more than the festive spirit.",
                "2021-11-24",
                8.3,
                "Action & Adventure, Drama"
            )
        )
        tvShow.add(
            TvShowEntity(
                1,
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/7vjaCdMw15FEbXyLQTVa04URsPm.jpg",
                "The Witcher",
                "Geralt of Rivia, a mutated monster-hunter for hire, journeys toward his destiny in a turbulent world where people often prove more wicked than beasts.",
                "2019-12-20",
                8.2,
                "Sci-Fi & Fantasy, Drama, Action & Adventure"
            )
        )
        return tvShow
    }

    fun generateRemoteDummyTvShows(): List<ResultsTvShow> {
        val tvShow = ArrayList<ResultsTvShow>()
        tvShow.add(
            ResultsTvShow(
                0,
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/pqzjCxPVc9TkVgGRWeAoMmyqkZV.jpg",
                "Hawkeye",
                "Former Avenger Clint Barton has a seemingly simple mission: get back to his family for Christmas. Possible? Maybe with the help of Kate Bishop, a 22-year-old archer with dreams of becoming a superhero. The two are forced to work together when a presence from Barton’s past threatens to derail far more than the festive spirit.",
                "2021-11-24",
                8.3,
            )
        )
        tvShow.add(
            ResultsTvShow(
                1,
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/7vjaCdMw15FEbXyLQTVa04URsPm.jpg",
                "The Witcher",
                "Geralt of Rivia, a mutated monster-hunter for hire, journeys toward his destiny in a turbulent world where people often prove more wicked than beasts.",
                "2019-12-20",
                8.2,
            )
        )
        return tvShow
    }

    fun generateRemoteDummyTvShowDetails(): TvShowDetailsResponse {

        return TvShowDetailsResponse(
            0,
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/pqzjCxPVc9TkVgGRWeAoMmyqkZV.jpg",
            "Hawkeye",
            "Former Avenger Clint Barton has a seemingly simple mission: get back to his family for Christmas. Possible? Maybe with the help of Kate Bishop, a 22-year-old archer with dreams of becoming a superhero. The two are forced to work together when a presence from Barton’s past threatens to derail far more than the festive spirit.",
            "2021-11-24",
            8.3,
            listOf(GenresTvShow("Action & Adventure"), GenresTvShow("Drama"))
        )
    }
}