package com.satya.subm.submission.data.remote.tvshow

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class TVShow(

    val poster_path: String?,
    val popularity: String?,
    val id: String,
    val backdrop_path: String?,
    val vote_average: String?,
    val overview: String?,
    val first_air_date: String?,

    val origin_country: Array<String>?,
    val genre_ids: Array<String>?,
    val original_language: String?,
    val vote_count: String?,
    val name: String?,
    val original_name: String?,
) : Parcelable {
    val baseUrl: String get() = "https://image.tmdb.org/t/p/w200"

}