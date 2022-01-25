package com.sahrul.moviecatalogue.utils

import com.sahrul.moviecatalogue.data.source.local.entity.MovieEntity
import com.sahrul.moviecatalogue.data.source.remote.response.GenresMovie
import com.sahrul.moviecatalogue.data.source.remote.response.MovieDetailsResponse
import com.sahrul.moviecatalogue.data.source.remote.response.ResultsMovie

object DummyMovies {

    fun generateDummyMovies(): List<MovieEntity> {
        val movies = ArrayList<MovieEntity>()
        movies.add(
            MovieEntity(
                0,
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                "Spider-Man: No Way Home",
                "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                "2021-12-15",
                8.5,
                "Action, Adventure, Science Fiction",
                "2h 28m"
            )
        )
        movies.add(
            MovieEntity(
                1,
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg",
                "Venom: Let There Be Carnage",
                "After finding a host body in investigative reporter Eddie Brock, the alien symbiote must face a new enemy, Carnage, the alter ego of serial killer Cletus Kasady.",
                "2021-09-30",
                7.1,
                "Science Fiction, Action, Adventure",
                "1h 37m"
            )
        )
        return movies
    }

    fun generateRemoteDummyMovies(): List<ResultsMovie> {
        val movies = ArrayList<ResultsMovie>()
        movies.add(
            ResultsMovie(
                0,
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                "Spider-Man: No Way Home",
                "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                "2021-12-15",
                8.5,
            )
        )
        movies.add(
            ResultsMovie(
                1,
                "https://image.tmdb.org/t/p/w600_and_h900_bestv2/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg",
                "Venom: Let There Be Carnage",
                "After finding a host body in investigative reporter Eddie Brock, the alien symbiote must face a new enemy, Carnage, the alter ego of serial killer Cletus Kasady.",
                "2021-09-30",
                7.1,
            )
        )
        return movies
    }

    fun generateRemoteDummyMovieDetails(): MovieDetailsResponse {

        return MovieDetailsResponse(
            0,
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
            "Spider-Man: No Way Home",
            "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
            "2021-12-15",
            8.5,
            listOf(GenresMovie("Action"), GenresMovie("Adventure"), GenresMovie("Science Fiction")),
            148
        )
    }
}