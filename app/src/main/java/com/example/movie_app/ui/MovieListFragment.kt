package com.example.movie_app.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment
import com.example.movie_app.R
import com.example.movie_app.data.models.Status
import com.example.movie_app.data.service.MovieApi
import com.example.movie_app.databinding.FragmentCoinListBinding
import com.example.movie_app.viewModel.MovieViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieListFragment: Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentCoinListBinding
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCoinListBinding.inflate(inflater, container, false)

       movieViewModel.movieList.observe(viewLifecycleOwner, Observer {
           when (it.status) {
               Status.SUCCESS -> {
                    Log.d("TEST4","Fragment success ${it.data!!.body()!!.results[1]}")
               }
               Status.LOADING -> {
                   Log.d("TEST4","Fragment loading ${it.status.toString()}")
               }
               else -> {
                   Log.d("TEST4","Fragment error ${it.status.toString()}")
               }
           }
       })

        binding.btnLogout.setOnClickListener {
            auth.signOut()
            NavHostFragment.findNavController(this).navigate(R.id.action_coinListFragment_to_loginFragment)

         // Log.d("TEST",api.getPopularMovies().toString())

        }







        // Inflate the layout for this fragment
        return binding.root
    }


}