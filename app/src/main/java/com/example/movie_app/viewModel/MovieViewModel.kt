package com.example.movie_app.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_app.data.models.MovieResponse
import com.example.movie_app.data.models.Resource
import com.example.movie_app.data.models.Status
import com.example.movie_app.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val MovieRepository:MovieRepository
): ViewModel(){

    private var movies:Resource<Response<MovieResponse>> = Resource(Status.LOADING,null,null)

    fun getMovies():Resource<Response<MovieResponse>>{
        viewModelScope.launch(Dispatchers.IO) {
           movies =  MovieRepository.getMovies()
        }
        return movies

    }

}