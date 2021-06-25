package com.izwin.topmovies.api

import com.izwin.topmovies.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("reviews/all.json")
    suspend fun getTopMovies(@Query("api-key") apiKey: String, @Query("offset") offset: Int): MovieResponse
}