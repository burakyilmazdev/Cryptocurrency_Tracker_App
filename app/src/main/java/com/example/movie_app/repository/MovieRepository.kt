package com.example.movie_app.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie_app.data.MoviesDataSource
import com.example.movie_app.data.models.MovieResponse
import com.example.movie_app.data.models.Resource
import com.example.movie_app.data.models.Result
import com.example.movie_app.data.models.Status
import com.example.movie_app.data.service.MovieApi
import com.example.movie_app.room.MovieDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val moviesDataSource: MoviesDataSource,private val movieDao: MovieDao
) {
    val allMovies : LiveData<List<Result>> = movieDao.getAllMovies()

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

    suspend fun addMovie(movie:Result){
        movieDao.addMovie(movie)
    }
    
    suspend fun deleteMovie(movie: Result){
        movieDao.delete(movie)
    }

    fun searchMovie(search:String): LiveData<List<Result>> {
        return movieDao.search(search)
    }




}