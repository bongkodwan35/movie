package com.example.aaaaaaa

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class mapMovieDB (
    @Expose
    @SerializedName("id") val id: Int,

    @Expose
    @SerializedName("title") val title: String,

    @Expose
    @SerializedName("year") val year: Int,

    @Expose
    @SerializedName("img_movie") val img_movie: String){}