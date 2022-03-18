package com.example.cryptocurrency_tracker_app.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.example.cryptocurrency_tracker_app.R
import com.example.cryptocurrency_tracker_app.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.createAccount.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_signUpFragment)
        }

        // Inflate the layout for this fragment
        return binding.root
    }


}