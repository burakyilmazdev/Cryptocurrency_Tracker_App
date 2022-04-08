package com.example.movie_app.ui.movieList

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_app.R
import com.example.movie_app.data.models.Result
import com.squareup.picasso.Picasso

class ViewPagerAdapter(private val listener : OnItemClickListener)
    :RecyclerView.Adapter<ViewPagerAdapter.ImageViewHolder>() {

    private val movieList = arrayListOf<Result>()

    @SuppressLint("NotifyDataSetChanged")
    fun setProductList(movieList : List<Result>){
        this.movieList.clear()
        this.movieList.addAll(movieList)
        notifyDataSetChanged()
    }

    class ImageViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView) {

        val imageView: ImageView = itemView.findViewById(R.id.moviePoster);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_pager_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Picasso.get().load("https://image.tmdb.org/t/p/w500/"+movieList[position].poster_path).into(holder.imageView)
        holder.imageView.setOnClickListener {
            listener.onItemClick(movieList[position])
        }

    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    interface OnItemClickListener{
        fun onItemClick(movie:Result)
    }



}