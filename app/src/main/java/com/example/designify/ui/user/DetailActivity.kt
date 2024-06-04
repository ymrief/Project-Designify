package com.example.designify.ui.user

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat.getParcelableExtra
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.designify.R
import com.example.designify.data.response.UrlResponse
import com.example.designify.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val photo = getParcelableExtra(
            intent,
            "content",
            UrlResponse::class.java
        ) as UrlResponse

        with(binding) {
            Glide.with(this@DetailActivity)
                .load(photo.urls.regular)
                .skipMemoryCache(true)
                .into(binding.imgCharacter)
            tvDescription.text = photo.description ?: "Unknown"
            tvCreatedAt.text = "Created at: ${photo.createdAt ?: "Unknown"}"
            tvLikes.text = "Likes: ${photo.likes ?: "Unknown"}"
            tvDownloads.text = "Downloads: ${photo.downloads ?: "Unknown"}"
            btnDownload.setOnClickListener() {
                Toast.makeText(this@DetailActivity, "Downloading", Toast.LENGTH_SHORT).show()
            }
        }
    }
}