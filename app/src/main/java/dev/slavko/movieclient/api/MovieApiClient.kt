package dev.slavko.movieclient.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class MovieApiClient {

    companion object {
        val BASE_URL = "https://api.themoviedb.org/3/"

        val instance: MovieDatabaseApi by lazy {
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

            retrofit.create(MovieDatabaseApi::class.java)
        }
    }
}