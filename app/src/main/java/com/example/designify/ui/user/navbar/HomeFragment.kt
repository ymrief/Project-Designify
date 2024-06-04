package com.example.designify.ui.user.navbar

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.designify.data.response.UrlResponse
import com.example.designify.data.retrofit.ApiConfig
import com.example.designify.databinding.FragmentHomeBinding
import com.example.designify.ui.adapter.PhotoAdapter
import com.example.designify.ui.user.DetailActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(), PhotoAdapter.OnItemClickListener {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val clientId = "YmFxFi2ZVCLDRx9tZV0bvdhERr6FrTzWhCmYGruJF8U"
        val count = 15

        val client = ApiConfig.getAPIService()
        client.getRandomPhoto(clientId, count).enqueue(object : Callback<ArrayList<UrlResponse>> {
            override fun onResponse(
                call: Call<ArrayList<UrlResponse>>,
                response: Response<ArrayList<UrlResponse>>
            ) {
                if (response.isSuccessful) {
                    val photoList: ArrayList<UrlResponse> = response.body() ?: arrayListOf()

                    with(binding) {
                        rvPhoto.setHasFixedSize(true)
                        rvPhoto.layoutManager= GridLayoutManager(requireContext(), 2)
                        rvPhoto.adapter = PhotoAdapter(photoList).apply {
                            listener = this@HomeFragment
                        }
                    }
                } else {
                    Toast.makeText(requireContext(), "onResponse: FAILURE!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<UrlResponse>>, t: Throwable) {
                Toast.makeText(requireContext(), "onFailure: FAILURE!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onItemClicked(item: UrlResponse) {
        val intent = Intent(requireActivity(), DetailActivity::class.java)
        intent.putExtra("content", item)
        startActivity(intent)
    }
}