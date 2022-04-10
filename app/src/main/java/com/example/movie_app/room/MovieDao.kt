package com.example.movie_app.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.movie_app.data.models.Result
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table ORDER BY id ASC")
    fun getAllMovies():LiveData<List<Result>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovie(movie : Result)

    @Delete
    suspend fun delete(movie: Result)

    @Query("SELECT * FROM movie_table WHERE title LIKE:searchQuery")
    fun search(searchQuery:String): LiveData<List<Result>>
}