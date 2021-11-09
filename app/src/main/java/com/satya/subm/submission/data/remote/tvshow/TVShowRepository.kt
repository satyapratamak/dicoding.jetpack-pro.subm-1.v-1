package com.satya.subm.submission.data.remote.tvshow


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.satya.subm.submission.data.remote.MoviePagingSource
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TVShowRepository @Inject constructor(private val tvShowAPI: TVShowAPI){

    fun getPopularTVShow() =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { TVShowPagingSource(tvShowAPI, null) }

        ).liveData

    fun searchPopularTVShow(query : String) =
        Pager(
            config = PagingConfig(
                pageSize = 5,
                maxSize = 20,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {TVShowPagingSource(tvShowAPI, query)}

        ).liveData

}