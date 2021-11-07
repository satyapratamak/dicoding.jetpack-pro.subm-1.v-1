package com.satya.subm.submission.data.remote


import com.satya.subm.submission.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = BuildConfig.MOVIEDB_API_KEY
    }

    @GET("movie/now_playing?api_key=$API_KEY")
    suspend fun getNowPlayingMovies(
        @Query("page") position: Int
    ) : MovieResponse

    @GET("search/movie?api_key=$API_KEY")
    suspend fun searchMovies(
        @Query("query") query : String,
        @Query("page") page : Int,

        ): MovieResponse


}