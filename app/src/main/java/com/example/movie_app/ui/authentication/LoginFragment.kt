package com.example.movie_app.ui.authentication

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.example.movie_app.R
import com.example.movie_app.data.service.MovieApi
import com.example.movie_app.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


class LoginFragment: Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(inflater, container, false)

        //navigate to create account
        binding.createAccount.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_signUpFragment)
        }


        val currentUser = auth.currentUser
        if (currentUser!=null){
            NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_coinListFragment)
        }

        //Login Process
        binding.btnLogin.setOnClickListener {

            if (TextUtils.isEmpty(binding.etMail.text.toString()) || TextUtils.isEmpty(binding.etPassword.text.toString())){
                Toast.makeText(context,"Email or password cannot be empty!", Toast.LENGTH_SHORT).show()
            }else{
                val userMail = binding.etMail.text.toString()
                val userPassword = binding.etPassword.text.toString()

                    auth.signInWithEmailAndPassword(userMail,userPassword)
                        .addOnCompleteListener() {
                            if (it.isSuccessful){
                                NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_coinListFragment)
                                Toast.makeText(context,"Password Correct!", Toast.LENGTH_SHORT).show()

                            }else{
                                Toast.makeText(context,"${it.exception?.localizedMessage}", Toast.LENGTH_SHORT).show()
                            }
                        }

            }

        }

        // Inflate the layout for this fragment
        return binding.root
    }


}