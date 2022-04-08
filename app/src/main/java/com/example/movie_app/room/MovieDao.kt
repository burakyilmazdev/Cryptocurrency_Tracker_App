package com.example.movie_app.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.movie_app.data.models.Result

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table ORDER BY id ASC")
    fun getAllMovies():LiveData<List<Result>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovie(movie : Result)

    @Delete
    fun delete(movie: Result)
}