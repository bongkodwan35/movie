package com.example.aaaaaaa

import retrofit2.Call
import retrofit2.http.GET

interface MoviesApi {
    @GET("allmovie") /// Call NodeJS
// @GET("allmovie.php") // Call PHP file
    fun retrieveMovie(): Call<List<mapMovieDB>>
}