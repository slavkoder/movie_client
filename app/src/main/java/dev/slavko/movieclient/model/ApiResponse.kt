package dev.slavko.movieclient.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.slavko.movieclient.api.MovieApiClient

@JsonClass(generateAdapter = true)
data class MovieResponse(val results: List<Movie>)

@JsonClass(generateAdapter = true)
data class Movie(@Json(name = "original_title") val originalTitle: String, val popularity: Double,
                 @Json(name = "poster_path") val posterPath: String, val overview: String) {

    // TODO: call configuration beforehand
    val posterUrl
        get() = "https://image.tmdb.org/t/p/w500" + posterPath
}