package com.example.movie_app.ui.movieList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_app.data.models.Result
import com.example.movie_app.databinding.RecyclerviewItemBinding
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(private val listener : OnItemClickListener):RecyclerView.Adapter<RecyclerViewAdapter.MovieListViewHolder>(){

    private val movieList = arrayListOf<Result>()

    @SuppressLint("NotifyDataSetChanged")
    fun setMovieList(movieList:List<Result>){
        this.movieList.clear()
        this.movieList.addAll(movieList)
        notifyDataSetChanged()
    }

    class MovieListViewHolder(private val binding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Result,listener: OnItemClickListener) {
            Picasso.get().load("https://image.tmdb.org/t/p/w400/" + movie.poster_path)
                .into(binding.rvImage)
            binding.movieName.text = movie.title
            binding.cardView.setOnClickListener {
                listener.onRecyclerViewItemClick(movie)
            }
        }

        companion object {

            fun create(parent: ViewGroup): MovieListViewHolder {
                val binding = RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context))
                return MovieListViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(movieList[position],listener)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    interface OnItemClickListener{
        fun onRecyclerViewItemClick(movie:Result)
    }
}