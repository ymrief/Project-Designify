package com.example.designify.ui.user.botnavbar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.designify.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val username = arguments?.getString("username")
//        val totalDownload = arguments?.getString("totalDownload")
//
//        Log.d("ProfileFragment", "Username: $username")
//
//        with(binding) {
//            tvUsername.text = username
//            tvTotalDownloads.text = totalDownload
//        }
//    }
}