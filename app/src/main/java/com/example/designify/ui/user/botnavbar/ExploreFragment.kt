package com.example.designify.ui.user.botnavbar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.designify.data.response.SearchResponse
import com.example.designify.data.response.UrlResponse
import com.example.designify.data.retrofit.ApiConfig
import com.example.designify.databinding.FragmentExploreBinding
import com.example.designify.ui.adapter.PhotoAdapter
import com.example.designify.ui.user.DetailActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExploreFragment : Fragment(), PhotoAdapter.OnItemClickListener {

    private lateinit var binding: FragmentExploreBinding

    val clientId = "YmFxFi2ZVCLDRx9tZV0bvdhERr6FrTzWhCmYGruJF8U"
    val count = 10
    var searchQuery: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val client = ApiConfig.getAPIService()

        with (binding) {
            ivSearch.setOnClickListener {
                searchQuery = etSearch.text.toString()
                if (searchQuery!!.isNotEmpty()) {
                    client.searchPhoto(clientId, searchQuery!!, count).enqueue(object : Callback<SearchResponse> {
                        override fun onResponse(
                            call: Call<SearchResponse>,
                            response: Response<SearchResponse>
                        ) {
                            if (response.isSuccessful) {
                                val photoList: ArrayList<UrlResponse> = response.body()?.result ?: arrayListOf()
                                rvPhoto.setHasFixedSize(true)
                                rvPhoto.layoutManager = GridLayoutManager(requireContext(), 2)
                                rvPhoto.adapter = PhotoAdapter(photoList).apply {
                                    listener = this@ExploreFragment
                                }
                            } else {
                                Toast.makeText(requireContext(), "onResponse: FAILURE!", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                            Log.d("onFailure", t.message.toString())
                            Toast.makeText(requireContext(), "onFailure: FAILURE!", Toast.LENGTH_SHORT).show()
                        }
                    })
                } else {
                    Toast.makeText(requireContext(), "Input is empty.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        client.getRandomPhoto(clientId, count)
            .enqueue(object : Callback<ArrayList<UrlResponse>> {
                override fun onResponse(
                    call: Call<ArrayList<UrlResponse>>,
                    response: Response<ArrayList<UrlResponse>>
                ) {
                    if (response.isSuccessful) {
                        val photoList: ArrayList<UrlResponse> = response.body() ?: arrayListOf()

                        with(binding) {
                            rvPhoto.setHasFixedSize(true)
                            rvPhoto.layoutManager = GridLayoutManager(requireContext(), 2)
                            rvPhoto.adapter = PhotoAdapter(photoList).apply {
                                listener = this@ExploreFragment
                            }
                        }
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "onResponse: FAILURE!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<ArrayList<UrlResponse>>, t: Throwable) {
                    Toast.makeText(requireContext(), "onFailure: FAILURE!", Toast.LENGTH_SHORT)
                        .show()
                }
            })
    }

    override fun onItemClicked(item: UrlResponse) {
        val intent = Intent(requireActivity(), DetailActivity::class.java)
        intent.putExtra("content", item)
        startActivity(intent)
    }
}