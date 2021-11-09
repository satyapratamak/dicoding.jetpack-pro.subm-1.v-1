package com.satya.subm.submission.data.remote.tvshow

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.satya.subm.submission.data.remote.Movie
import com.satya.subm.submission.data.remote.MovieAPI

import retrofit2.HttpException
import java.io.IOException


private const val STARTING_PAGE_INDEX = 1
class TVShowPagingSource (
    private val tvshowAPI: TVShowAPI,
    private val query: String?,
) : PagingSource<Int, TVShow>() {
    override fun getRefreshKey(state: PagingState<Int, TVShow>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVShow> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val response = if (query != null) tvshowAPI.searchTVShow(
                query,
                position
            ) else tvshowAPI.getPopularTVShow(position)
            val tvShow = response.results
            LoadResult.Page(
                data = tvShow,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (tvShow.isEmpty()) null else position + 1
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}