package com.satya.subm.submission.ui.tvshow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.satya.subm.submission.R
import com.satya.subm.submission.data.remote.Movie
import com.satya.subm.submission.databinding.FragmentTvshowBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TVShowFragment : Fragment(R.layout.fragment_tvshow), TVShowAdapter.OnItemClickListener{

    private val viewModel by viewModels<TVShowViewModel>()
    private var _binding:FragmentTvshowBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentTvshowBinding.bind(view)
        val adapter = TVShowAdapter(this)

        binding.apply {
            rvTvshow.setHasFixedSize(true)
            rvTvshow.adapter = adapter

        }

        viewModel.tvshows.observe(viewLifecycleOwner){
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

    }

    override fun onItemClick(movie: Movie, duration: String) {
        TODO("Not yet implemented")
    }

}