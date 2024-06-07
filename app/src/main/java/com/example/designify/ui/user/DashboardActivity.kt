package com.example.designify.ui.user

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.designify.R
import com.example.designify.databinding.ActivityDashboardBinding
import com.example.designify.ui.user.botnavbar.ProfileFragment

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        val username = intent.getStringExtra("username")

        with (binding) {
            val navHostFragment = supportFragmentManager.findFragmentById(fcvMain.id) as NavHostFragment
            bnvMain.setupWithNavController(navHostFragment.navController)

//            Method 2 (ERROR)
//            bnvMain.setOnClickListener {
//                val bundle = Bundle().apply {
//                    putString("username", username)
//                }
//                ProfileFragment().apply {
//                    arguments = bundle
//                }
//            }

//            Method 1 (ERROR)
//            Log.d("DashboardActivity", "Username: $username")
//
//            val navController = navHostFragment.navController
//
//            bnvMain.setOnNavigationItemSelectedListener { item ->
//                val bundle = Bundle()
//                bundle.putString("username", username)
//
//                navController.navigate(item.itemId, bundle)
//
//                true
//            }
//
//            bnvMain.setupWithNavController(navController)
        }
    }
}