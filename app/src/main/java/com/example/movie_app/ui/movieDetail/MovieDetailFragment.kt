package com.example.movie_app.ui.movieDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.movie_app.databinding.FragmentMovieDetailBinding
import com.squareup.picasso.Picasso


class MovieDetailFragment : Fragment() {

    private lateinit var binding : FragmentMovieDetailBinding
    private val args by navArgs<MovieDetailFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        binding.movieSubject.text = args.movie.overview
        Picasso.get().load("https://image.tmdb.org/t/p/w500/" + args.movie.poster_path).into(binding.movieDetail)
        binding.movieTitle.text = args.movie.title
        // Inflate the layout for this fragment
        return binding.root
    }





}