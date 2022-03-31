package com.example.movie_app.data

import com.example.movie_app.data.models.MovieResponse
import com.example.movie_app.data.models.Resource
import com.example.movie_app.data.models.Status
import com.example.movie_app.data.service.MovieApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class MoviesDataSource @Inject constructor(
    private val api: MovieApi
) {

    suspend fun getResponse() : Resource<Response<MovieResponse>>{
        val response = api.getPopularMovies()
        return if (response.isSuccessful){
            Resource(Status.SUCCESS,response,null)
        }else{
            Resource(Status.ERROR,null,response.message())
        }

    }
}