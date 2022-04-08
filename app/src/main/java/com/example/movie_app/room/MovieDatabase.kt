package com.example.movie_app.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movie_app.data.models.Result


@Database(entities = [Result::class],version = 1,exportSchema = false)
abstract class MovieDatabase: RoomDatabase()  {

    abstract fun getMovieDao():MovieDao

    companion object{

        @Volatile
        private var INSTANCE:MovieDatabase?=null

        fun getMovieDatabase(context:Context):MovieDatabase{
            val tempInstance = INSTANCE
            if (tempInstance !=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }

    }


}