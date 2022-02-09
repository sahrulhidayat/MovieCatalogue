package com.sahrul.moviecatalogue.ui.favorite.movie

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sahrul.moviecatalogue.data.source.local.entity.MovieEntity
import com.sahrul.moviecatalogue.databinding.ItemsListBinding
import com.sahrul.moviecatalogue.ui.detail.DetailActivity
import com.sahrul.moviecatalogue.utils.Consts.BASE_IMAGE_URL
import com.sahrul.moviecatalogue.utils.loadImage

class FavoriteMovieAdapter(private val tabId: Int) :
    PagedListAdapter<MovieEntity, FavoriteMovieAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    inner class ViewHolder(private val binding: ItemsListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            val context = itemView.context

            with(binding) {
                context.loadImage(BASE_IMAGE_URL + movie.image, imgPoster)
                tvTitle.text = movie.title
                tvReleaseDate.text = movie.release
                tvRatings.text = movie.ratings.toString()
            }

            onClickItem(itemView, movie, context)
        }
    }

    private fun onClickItem(view: View, movie: MovieEntity, context: Context) {
        view.setOnClickListener {
            val intentDetail = Intent(context, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_ID, movie.id)
                putExtra(DetailActivity.EXTRA_TAB, tabId)
            }
            context.startActivity(intentDetail)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

}
