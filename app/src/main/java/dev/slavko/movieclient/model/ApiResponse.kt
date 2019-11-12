package dev.slavko.movieclient.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse(val results: List<Movie>)

@JsonClass(generateAdapter = true)
data class Movie(@Json(name = "original_title") val originalTitle: String, val popularity: Double)