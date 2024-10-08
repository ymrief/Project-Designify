package com.example.designify.ui.welcome.auth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.designify.R
import com.example.designify.data.DatabaseHelper
import com.example.designify.databinding.FragmentLoginBinding
import com.example.designify.ui.user.DashboardActivity
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        databaseHelper = DatabaseHelper(requireContext())

        var username = arguments?.getString("username")
        var password = arguments?.getString("password")

        with(binding) {
            tvGoRegister.setOnClickListener {
                val registerFragment = RegisterFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, registerFragment)
                    .addToBackStack(null)
                    .commit()
            }

            etEmail.setText(username)
            etPassword.setText(password)

            btnLogin.setOnClickListener {
                username = etEmail.text.toString()
                password = etPassword.text.toString()

                if (username!!.isEmpty() || password!!.isEmpty()) {
                    Snackbar.make(
                        requireView(),
                        "Username dan password harus diisi!",
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else {
                    signinDatabase(username!!, password!!)
                }
            }
        }
    }

    private fun signinDatabase(username: String, password: String) {
        val userExists = databaseHelper.readUser(username, password)
        if (userExists) {
            Snackbar.make(
                requireView(),
                "Login berhasil!",
                Snackbar.LENGTH_SHORT
            ).show()
            val intent = Intent(requireContext(), DashboardActivity::class.java).apply {
                putExtra("username", username)
            }
            startActivity(intent)
        } else {
            Snackbar.make(
                requireView(),
                "Login gagal!",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}
