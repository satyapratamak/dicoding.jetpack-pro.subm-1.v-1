package com.satya.subm.submission.data.remote.tvshow

import com.satya.subm.submission.BuildConfig

import com.satya.subm.submission.data.remote.MovieResponse

import retrofit2.http.GET
import retrofit2.http.Query

interface TVShowAPI {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = BuildConfig.MOVIEDB_API_KEY
    }

    @GET("tv/popular?api_key=${TVShowAPI.API_KEY}")
    suspend fun getPopularTVShow(
        @Query("page") position: Int
    ) : TVShowResponse


    @GET("search/tv?api_key=${TVShowAPI.API_KEY}")
    suspend fun searchTVShow(
        @Query("query") query : String,
        @Query("page") page : Int,

        ): TVShowResponse

}