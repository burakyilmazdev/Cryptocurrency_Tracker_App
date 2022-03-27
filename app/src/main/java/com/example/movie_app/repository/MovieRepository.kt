package com.example.movie_app.repository

import com.example.movie_app.data.models.MovieResponse
import com.example.movie_app.data.models.Resource
import com.example.movie_app.data.models.Status
import com.example.movie_app.data.service.MovieApi
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api:MovieApi
) {
    suspend fun getMovies(): Resource<Response<MovieResponse>> {
       val response = api.getPopularMovies()
        return if (response.isSuccessful){
            Resource(Status.SUCCESS,response,null)
        }else{
            Resource(Status.ERROR,null,response.message())
        }
    }
}