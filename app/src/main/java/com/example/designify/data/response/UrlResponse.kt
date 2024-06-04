package com.example.designify.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UrlResponse(

    @SerializedName("id")
    val id: String? = null,

    @SerializedName("urls")
    val urls: PhotoResponse,

    @SerializedName("description")
    val description: String? = null,

    @SerializedName("created_at")
    val createdAt: String? = null,

    @SerializedName("likes")
    val likes: Int? = null,

    @SerializedName("downloads")
    val downloads: Int? = null

): Parcelable
