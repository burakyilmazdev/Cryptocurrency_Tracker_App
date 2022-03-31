package com.example.movie_app.repository

import com.example.movie_app.data.MoviesDataSource
import com.example.movie_app.data.models.MovieResponse
import com.example.movie_app.data.models.Resource
import com.example.movie_app.data.models.Status
import com.example.movie_app.data.service.MovieApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val moviesDataSource: MoviesDataSource
) {

    suspend fun getMovies(): Flow<Resource<Response<MovieResponse>>> {

        return flow {
            emit(Resource.loading())
            if (moviesDataSource.getResponse().status == Status.SUCCESS){
                emit(moviesDataSource.getResponse())
            }
            else{
                emit(Resource.error(moviesDataSource.getResponse().message))
            }

        }
    }
}