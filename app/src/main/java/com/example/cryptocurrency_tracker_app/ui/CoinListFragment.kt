package com.example.cryptocurrency_tracker_app.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.cryptocurrency_tracker_app.R
import com.example.cryptocurrency_tracker_app.databinding.FragmentCoinListBinding
import com.example.cryptocurrency_tracker_app.databinding.FragmentLoginBinding
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







        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coin_list, container, false)
    }


}