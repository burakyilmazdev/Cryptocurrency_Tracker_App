package com.example.movie_app.ui.favoriteMovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.movie_app.R
import com.example.movie_app.databinding.FragmentFavoriteBinding
import com.example.movie_app.databinding.FragmentMovieDetailBinding
import com.example.movie_app.viewModel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding:FragmentFavoriteBinding
    private val movieViewModel: MovieViewModel by viewModels()
    private val favoriteAdapter=FavoriteAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieViewModel.favoriteMovies.observe(this, Observer {
            favoriteAdapter.setMovieList(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        binding.rvFavorites.adapter = favoriteAdapter

        // Inflate the layout for this fragment
        return binding.root
    }

}