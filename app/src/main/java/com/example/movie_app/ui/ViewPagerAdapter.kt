package com.example.movie_app.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.movie_app.R
import com.example.movie_app.data.models.Result
import com.example.movie_app.databinding.ViewPagerItemBinding
import com.squareup.picasso.Picasso

class ViewPagerAdapter()
    :RecyclerView.Adapter<ViewPagerAdapter.ImageViewHolder>() {

    private val movieList = arrayListOf<Result>()

    @SuppressLint("NotifyDataSetChanged")
    fun setProductList(productList : List<Result>){
        this.movieList.clear()
        this.movieList.addAll(productList)
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
    }

    override fun getItemCount(): Int {
        return movieList.size
    }



}