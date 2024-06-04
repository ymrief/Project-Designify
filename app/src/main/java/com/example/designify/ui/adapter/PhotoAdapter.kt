package com.example.designify.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.designify.data.response.UrlResponse
import com.example.designify.databinding.ItemPhotoBinding

class PhotoAdapter(private val photosList: ArrayList<UrlResponse>)
    : RecyclerView.Adapter<PhotoAdapter.CustomViewHolder>() {

    lateinit var listener: OnItemClickListener

    inner class CustomViewHolder(private val binding: ItemPhotoBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: UrlResponse) {
            Glide.with(itemView.context)
                .load(photo.urls.regular)
                .into(binding.ivPhoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            ItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return photosList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(photosList[position])
        holder.itemView.setOnClickListener {
            listener.onItemClicked(photosList[position])
        }
    }

    interface OnItemClickListener {
        fun onItemClicked(item: UrlResponse)
    }
}