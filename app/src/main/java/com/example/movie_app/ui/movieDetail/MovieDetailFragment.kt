package com.example.movie_app.ui.movieDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.movie_app.R
import com.example.movie_app.databinding.FragmentMovieDetailBinding
import com.example.movie_app.ui.movieList.MovieListFragmentDirections
import com.example.movie_app.viewModel.MovieViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private lateinit var binding : FragmentMovieDetailBinding
    private val args by navArgs<MovieDetailFragmentArgs>()
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        activity?.findViewById<BottomNavigationView>(R.id.bottomNav)?.visibility = View.VISIBLE

        binding.movieSubject.text = args.movie.overview
        Picasso.get().load("https://image.tmdb.org/t/p/w500/" + args.movie.poster_path).into(binding.movieDetail)
        binding.movieTitle.text = args.movie.title
        binding.addToFavoritesButton.setOnClickListener {
            Toast.makeText(context,"Added to Favorites!",Toast.LENGTH_SHORT).show()
            movieViewModel.addMovie(args.movie)
        }
        // Inflate the layout for this fragment
        return binding.root
    }





}