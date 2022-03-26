package com.example.movie_app.data.service

import com.example.movie_app.data.models.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET

interface MovieApi {

    @GET("top_rated?api_key=10f8ed8f46cea198f9dd2129bbcd37a8")
    fun getNewProducts(): Single<List<MovieResponse>>


}