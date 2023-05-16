package com.example.shopapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.shopapp.R
import com.example.shopapp.databinding.FragmentSignIn2Binding
import com.example.shopapp.databinding.FragmentSignUp2Binding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth


class SignInFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var navControl: NavController
    private lateinit var binding: FragmentSignIn2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignIn2Binding.inflate(inflater, container, false)
        return binding.root}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init(view)
        registerEvents()
    }

    private fun init(view: View) {
        navControl = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun registerEvents() {

        binding.textViewSignUp.setOnClickListener {
            navControl.navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        binding.nextBtn.setOnClickListener {
            val email = binding.emailEt.text.toString().trim()
            val pass = binding.passEt.text.toString().trim()


            if (email.isNotEmpty() && pass.isNotEmpty()) {
                binding.progressBar.visibility = View.VISIBLE
                    auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(
                        OnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(context, "Login Successfully", Toast.LENGTH_SHORT).show()
                                navControl.navigate(R.id.action_signInFragment_to_homeFragment)
                            }else{
                                Toast.makeText(context, "There is no user with such record", Toast.LENGTH_SHORT).show()
                            }
                            binding.progressBar.visibility = View.GONE
                        }
                    )
            }else {
                Toast.makeText(context, "Empty fields not allowed", Toast.LENGTH_SHORT).show()
            }
        }
    }

}