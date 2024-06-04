package com.example.designify.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoResponse(

    @SerializedName("regular")
    val regular: String? = null

): Parcelable
