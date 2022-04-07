package com.example.movie_app.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.NavAction
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.movie_app.R
import com.example.movie_app.data.models.Result
import com.example.movie_app.data.models.Status
import com.example.movie_app.data.service.MovieApi
import com.example.movie_app.databinding.FragmentMovieListBinding
import com.example.movie_app.viewModel.MovieViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.Disposable
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.abs

@AndroidEntryPoint
class MovieListFragment : Fragment(), ViewPagerAdapter.OnItemClickListener{

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentMovieListBinding
    private val movieViewModel: MovieViewModel by viewModels()
    private var viewPagerAdapter = ViewPagerAdapter(this)
    private lateinit var viewPager2: ViewPager2
    private lateinit var handler: Handler
    private val recyclerViewAdapter = RecyclerViewAdapter()
    private val MovieDetailFragment = MovieDetailFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        handler = Handler(Looper.myLooper()!!)

        movieViewModel.movieList.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.d("TEST4", "Fragment Success ${it.data.toString()}")
                    val movieList = it.data!!.body()!!.results
                    viewPagerAdapter.setProductList(movieList)
                    recyclerViewAdapter.setMovieList(movieList)
                }
                Status.LOADING -> {
                    Log.d("TEST4", "Fragment loading ${it.status.toString()}")
                }
                else -> {
                    Log.d("TEST4", "Fragment error ${it.status.toString()}")
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        viewPager2 = binding.movieViewPager
        binding.movieRecyclerView.adapter = recyclerViewAdapter

        viewPager2.adapter = viewPagerAdapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }
        viewPager2.setPageTransformer(transformer)

        return binding.root
    }

    override fun onItemClick(movie: Result) {
        Log.d("TEST5","Click Item ${movie.title}")
        Toast.makeText(context,"Click ${movie.title}",Toast.LENGTH_SHORT).show()
        val action = MovieListFragmentDirections.actionCoinListFragmentToMovieDetailFragment(movie)
        findNavController().navigate(action)
    }




}