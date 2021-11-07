package com.satya.subm.submission.ui.details

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.satya.subm.submission.R
import com.satya.subm.submission.databinding.FragmentDetailsBinding
import kotlinx.android.synthetic.main.fragment_movie.*

class DetailsFragment : Fragment(R.layout.fragment_details){

    private val args by navArgs<DetailsFragmentArgs>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentDetailsBinding.bind(view)
        binding.apply {
            val movie = args.movie
            val duration = args.duration

            Glide.with(this@DetailsFragment)
                .load("${movie.baseUrl}${movie.poster_path}")
                .error(R.drawable.ic_error)
                .listener(object : RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.isVisible = false
                        tvTitle.isVisible = true
                        tvDuration.isVisible = true
                        tvOverview.isVisible = true
                        return false
                    }

                }).into(ivMoviePoster)

            tvTitle.text = movie.title
            tvOverview.text = movie.overview
            tvDuration.text = "Duration : $duration minutes"
        }
    }
}