package com.satya.subm.submission.ui.movie

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.annotation.RequiresApi
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.satya.subm.submission.R
import com.satya.subm.submission.data.remote.Movie
import com.satya.subm.submission.databinding.ItemMovieBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieAdapter(private val listener : OnItemClickListener) : PagingDataAdapter<Movie, MovieAdapter.MovieViewHolder>(COMPARATOR) {

    inner class MovieViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val randDuration = rand(60, 240)
        init{

            binding.root.setOnClickListener{
                val position = bindingAdapterPosition

                if (position != RecyclerView.NO_POSITION){
                    val item = getItem(position)
                    if (item != null){
                        listener.onItemClick(item, randDuration.toString())
                    }
                }
            }
        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(movie: Movie) {
            with(binding) {
                Glide.with(itemView)
                    .load("${movie.baseUrl}${movie.poster_path}")
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(ivMoviePoster)
                tvMovieTitle.text = movie.original_title
                tvOverview.text = movie.overview
                if (movie.original_language == "en"){
                    tvLanguage.text = "Languange : English"
                }

                var date = LocalDate.parse(movie.release_date)
                tvReleaseDate.text = " Release Date : ${date.dayOfMonth.toString()} ${date.month.toString()} ${date.year.toString()} "
                if (movie.adult == "false"){
                    tvRatingUmur.text = "Semua Umur"
                    tvRatingUmur.setTextColor(Color.parseColor("#0B6623"))
                }else{
                    tvRatingUmur.text = "Dewasa"
                    tvRatingUmur.setTextColor(Color.parseColor("#d42e12"))

                }

                //val randDuration = rand(60, 240)
                tvDuration.text = "Duration : ${randDuration.toString()} minutes"

                tvPopularity.text = "Popularity : ${movie.popularity}"
                tvVoteAvg.text = "Vote AVG : ${movie.vote_average}"
                tvVoteCount.text = "Vote Count : ${movie.vote_count}"




            }
        }
    }

    fun rand(start: Int, end: Int): Int {
        require(start <= end) { "Illegal Argument" }
        return (Math.random() * (end - start + 1)).toInt() + start
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    // checking duplicate data
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem

        }
    }

    interface OnItemClickListener{
        fun onItemClick(movie : Movie, duration : String)
    }


}