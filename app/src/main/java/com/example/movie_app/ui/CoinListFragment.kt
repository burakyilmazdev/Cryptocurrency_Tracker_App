package com.example.movie_app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.example.movie_app.R
import com.example.movie_app.databinding.FragmentCoinListBinding
import com.google.firebase.auth.FirebaseAuth

class CoinListFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentCoinListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCoinListBinding.inflate(inflater, container, false)

        binding.btnLogout.setOnClickListener {
            auth.signOut()
            NavHostFragment.findNavController(this).navigate(R.id.action_coinListFragment_to_loginFragment)

        }







        // Inflate the layout for this fragment
        return binding.root
    }


}