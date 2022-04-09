package com.example.movie_app.ui.favoriteMovies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_app.data.models.Result
import com.example.movie_app.databinding.FavoriteItemBinding
import com.example.movie_app.databinding.RecyclerviewItemBinding
import com.example.movie_app.ui.movieList.RecyclerViewAdapter
import com.example.movie_app.utility.SwipeGesture
import com.squareup.picasso.Picasso

class FavoriteAdapter:RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    val movieList = arrayListOf<Result>()

    @SuppressLint("NotifyDataSetChanged")
    fun setMovieList(movieList:List<Result>){
        this.movieList.clear()
        this.movieList.addAll(movieList)
        notifyDataSetChanged()
    }


    class FavoriteViewHolder(private val binding: FavoriteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Result) {
            Picasso.get().load("https://image.tmdb.org/t/p/w300/" + movie.poster_path)
                .into(binding.favoriteImage)
            binding.favoriteTitle.text = movie.title
        }

        companion object {

            fun create(parent: ViewGroup): FavoriteAdapter.FavoriteViewHolder {
                val binding = FavoriteItemBinding.inflate(LayoutInflater.from(parent.context))
                return FavoriteAdapter.FavoriteViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

}