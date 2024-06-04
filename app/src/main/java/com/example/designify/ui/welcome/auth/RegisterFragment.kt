package com.example.designify.ui.welcome.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.designify.R
import com.example.designify.databinding.FragmentRegisterBinding
import com.google.android.material.snackbar.Snackbar

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnRegister.setOnClickListener {
                val username = etEmail.text.toString()
                val password = etPassword.text.toString()
                val confirmPassword = etConfirmPassword.text.toString()

                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    Snackbar.make(
                        requireView(),
                        "Field tidak boleh kosong!",
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else if (password == confirmPassword) {
                    val bundle = Bundle().apply {
                        putString("username", username)
                        putString("password", password)
                    }
                    val loginFragment = LoginFragment().apply {
                        arguments = bundle
                    }
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, loginFragment)
                        .addToBackStack(null)
                        .commit()
                } else {
                    Snackbar.make(
                        requireView(),
                        "Password tidak sesuai!",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }

            tvLogin.setOnClickListener {
                val loginFragment = LoginFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, loginFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
}