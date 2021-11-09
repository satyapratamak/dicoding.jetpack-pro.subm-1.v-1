package com.satya.subm.submission.ui.tvshow

import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.satya.subm.submission.R
import com.satya.subm.submission.data.remote.Movie
import com.satya.subm.submission.data.remote.tvshow.TVShow
import com.satya.subm.submission.databinding.ItemMovieBinding
import com.satya.subm.submission.databinding.ItemTvshowBinding
import com.satya.subm.submission.ui.movie.MovieAdapter
import java.time.LocalDate

class TVShowAdapter (private val listener : OnItemClickListener) : PagingDataAdapter<TVShow, TVShowAdapter.TVShowViewHolder>(COMPARATOR){

    inner class TVShowViewHolder(private val binding: ItemTvshowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        //val randDuration = rand(60, 240)


//        init{
//              //OnClickListener dari Item TV Show
//        // aktifkan NAvigation terlebih dahulu
//            binding.root.setOnClickListener{
//                val position = bindingAdapterPosition
//
//                if (position != RecyclerView.NO_POSITION){
//                    val item = getItem(position)
//                    if (item != null){
//                        listener.onItemClick(item, randDuration.toString())
//                    }
//                }
//            }
//        }

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(tvshow: TVShow) {
            with(binding) {
                Glide.with(itemView)
                    .load("${tvshow.baseUrl}${tvshow.poster_path}")
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(ivTvshowPoster)
//                tvMovieTitle.text = movie.original_title
//                tvOverview.text = movie.overview
//                if (movie.original_language == "en"){
//                    tvLanguage.text = "Languange : English"
//                }
//
//                var date = LocalDate.parse(movie.release_date)
//                tvReleaseDate.text = " Release Date : ${date.dayOfMonth.toString()} ${date.month.toString()} ${date.year.toString()} "
//                if (movie.adult == "false"){
//                    tvRatingUmur.text = "Semua Umur"
//                    tvRatingUmur.setTextColor(Color.parseColor("#0B6623"))
//                }else{
//                    tvRatingUmur.text = "Dewasa"
//                    tvRatingUmur.setTextColor(Color.parseColor("#d42e12"))
//
//                }
//
//                //val randDuration = rand(60, 240)
//                tvDuration.text = "Duration : ${randDuration.toString()} minutes"
//
//                tvPopularity.text = "Popularity : ${movie.popularity}"
//                tvVoteAvg.text = "Vote AVG : ${movie.vote_average}"
//                tvVoteCount.text = "Vote Count : ${movie.vote_count}"




            }
        }
    }

//    fun rand(start: Int, end: Int): Int {
//        require(start <= end) { "Illegal Argument" }
//        return (Math.random() * (end - start + 1)).toInt() + start
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVShowViewHolder {
        val binding = ItemTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVShowViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    // checking duplicate data
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<TVShow>() {
            override fun areItemsTheSame(oldItem: TVShow, newItem: TVShow): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TVShow, newItem: TVShow): Boolean =
                oldItem == newItem

        }
    }

    interface OnItemClickListener{
        fun onItemClick(movie : Movie, duration : String)
    }
}