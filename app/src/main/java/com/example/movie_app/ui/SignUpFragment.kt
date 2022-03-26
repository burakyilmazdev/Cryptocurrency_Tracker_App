package com.example.movie_app.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.example.movie_app.R
import com.example.movie_app.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth


class SignUpFragment : Fragment() {

    private lateinit var binding:FragmentSignUpBinding
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAuth = FirebaseAuth.getInstance()

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        binding.tvAlreadyRegistered.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_signUpFragment_to_loginFragment)
        }

        binding.btnSignUp.setOnClickListener {

            if (TextUtils.isEmpty(binding.etMail.text.toString()) || TextUtils.isEmpty(binding.etPassword.text.toString())
                || TextUtils.isEmpty(binding.etSecondPassword.text.toString()) ){
                Toast.makeText(context,"Email or password cannot be empty!",Toast.LENGTH_SHORT).show()
            }else{
                val userMail = binding.etMail.text.toString()
                val userPassword = binding.etPassword.text.toString()
                val userSecondPassword = binding.etSecondPassword.text.toString()

                if (userPassword==userSecondPassword){
                    mAuth.createUserWithEmailAndPassword(userMail,userPassword)
                        .addOnCompleteListener() {
                            if (it.isSuccessful){
                                Toast.makeText(context,"User has been created!",Toast.LENGTH_SHORT).show()
                                NavHostFragment.findNavController(this).navigate(R.id.action_signUpFragment_to_coinListFragment)

                            }else{
                                Toast.makeText(context,"${it.exception?.localizedMessage}",Toast.LENGTH_SHORT).show()
                            }
                        }
                }else{
                    Toast.makeText(context,"Passwords aren't matched!",Toast.LENGTH_SHORT).show()
                }
            }

        }

        // Inflate the layout for this fragment
        return binding.root
    }
}