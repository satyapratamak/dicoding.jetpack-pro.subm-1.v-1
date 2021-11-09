package com.satya.subm.submission.ui.tvshow

import androidx.hilt.Assisted
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.satya.subm.submission.data.remote.tvshow.TVShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class TVShowViewModel@Inject constructor(
    private val repository: TVShowRepository,
    @Assisted state: SavedStateHandle
) : ViewModel()  {

    companion object {
        private const val CURRENT_QUERY = "current_query"
        private const val EMPTY_QUERY = ""
    }

    private val currentQuery = state.getLiveData(CURRENT_QUERY, EMPTY_QUERY)

    val tvshows = currentQuery.switchMap { query ->
        if (!query.isEmpty()) {
            repository.searchPopularTVShow(query)
        } else {
            repository.getPopularTVShow().cachedIn(viewModelScope)
        }
    }

    fun searchMovies(query : String){
        currentQuery.value = query
    }

}