package com.example.movie_app.di

import android.app.Application
import android.content.Context
import com.example.movie_app.data.MoviesDataSource
import com.example.movie_app.data.service.MovieApi
import com.example.movie_app.repository.MovieRepository
import com.example.movie_app.room.MovieDao
import com.example.movie_app.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    val BASE_URL = "https://api.themoviedb.org/3/movie/"


    @Singleton
    @Provides
    fun provideMovieApi() : MovieApi = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
        .create(MovieApi::class.java)

    @Singleton
    @Provides
    fun provideMovieRepository(moviesDataSource: MoviesDataSource,movieDao:MovieDao) : MovieRepository{
        return MovieRepository(moviesDataSource,movieDao)
    }

    @Singleton
    @Provides
    fun provideMovieDataSource(api: MovieApi): MoviesDataSource{
        return MoviesDataSource(api)
    }

    @Singleton
    @Provides
    fun provideDatabase(context: Application):MovieDatabase{
        return MovieDatabase.getMovieDatabase(context)
    }

    @Singleton
    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase):MovieDao{
        return movieDatabase.getMovieDao()
    }




}