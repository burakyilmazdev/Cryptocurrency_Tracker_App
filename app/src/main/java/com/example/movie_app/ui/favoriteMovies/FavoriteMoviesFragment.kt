package com.example.movie_app.ui.favoriteMovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.movie_app.R
import com.example.movie_app.databinding.FragmentFavoriteMoviesBinding
import com.example.movie_app.databinding.FragmentMovieDetailBinding
import com.example.movie_app.ui.movieDetail.MovieDetailFragmentArgs
import com.example.movie_app.viewModel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteMoviesFragment : Fragment() {

    private lateinit var binding:FragmentFavoriteMoviesBinding
    private val movieViewModel: MovieViewModel by viewModels()
    private val favoriteMoviesAdapter = FavoriteMoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieViewModel.favoriteMovies.observe(viewLifecycleOwner, Observer {
            favoriteMoviesAdapter.setMovieList(it)
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteMoviesBinding.inflate(inflater, container, false)
        binding.rvFavorites.adapter = favoriteMoviesAdapter

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movies, container, false)
    }

}