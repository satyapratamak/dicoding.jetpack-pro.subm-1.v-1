package com.satya.subm.submission.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val movieAPI: MovieAPI) {
    fun getNowPlayingMovies() =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
            enablePlaceholders = false,
            ),
            pagingSourceFactory = {MoviePagingSource(movieAPI, null)}

        ).liveData

    fun searchMovies(query : String) =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {MoviePagingSource(movieAPI, query)}

        ).liveData
}