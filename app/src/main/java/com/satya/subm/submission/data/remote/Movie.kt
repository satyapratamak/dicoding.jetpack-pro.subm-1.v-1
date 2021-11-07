package com.satya.subm.submission.data.remote

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val adult: String?,
    val backdrop_path: String?,
    val id: String,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: String?,

    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: String?,
    val vote_average: String?,
    val vote_count: String?,

    ) : Parcelable{
    val baseUrl: String get() = "https://image.tmdb.org/t/p/w200"

}
