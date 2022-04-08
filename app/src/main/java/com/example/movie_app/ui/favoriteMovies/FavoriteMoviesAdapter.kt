package com.example.movie_app.ui.favoriteMovies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_app.data.models.Result
import com.example.movie_app.databinding.FavoriteMoviesItemBinding
import com.example.movie_app.databinding.FragmentFavoriteMoviesBinding
import com.example.movie_app.databinding.RecyclerviewItemBinding
import com.example.movie_app.ui.movieList.RecyclerViewAdapter
import com.squareup.picasso.Picasso

class FavoriteMoviesAdapter():RecyclerView.Adapter<FavoriteMoviesAdapter.FavoriteMoviesViewHolder>() {

    private val movieList = arrayListOf<Result>()

    @SuppressLint("NotifyDataSetChanged")
    fun setMovieList(movieList:List<Result>){
        this.movieList.clear()
        this.movieList.addAll(movieList)
        notifyDataSetChanged()
    }


    class FavoriteMoviesViewHolder(private val binding: FavoriteMoviesItemBinding)
        :RecyclerView.ViewHolder(binding.root){

            fun bind(movie:Result){
                Picasso.get().load("https://image.tmdb.org/t/p/w400/" + movie.poster_path)
                    .into(binding.favoriteMovieImage)
                binding.favoriteMovieName.text = movie.title
            }

        companion object{

            fun create(parent: ViewGroup): FavoriteMoviesViewHolder {
                val binding = FavoriteMoviesItemBinding.inflate(LayoutInflater.from(parent.context))
                return FavoriteMoviesViewHolder(binding)
            }
        }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMoviesViewHolder {
        return FavoriteMoviesViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: FavoriteMoviesViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }


}