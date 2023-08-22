package com.sahrul.moviecatalogue.ui.home.movie

import android.content.Context
import android.content.Intent
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.compose.rememberAsyncImagePainter
import com.sahrul.moviecatalogue.data.source.local.entity.MovieEntity
import com.sahrul.moviecatalogue.ui.component.ItemCard
import com.sahrul.moviecatalogue.ui.detail.DetailActivity
import com.sahrul.moviecatalogue.ui.detail.DetailActivity.Companion.EXTRA_ID
import com.sahrul.moviecatalogue.ui.detail.DetailActivity.Companion.EXTRA_TAB
import com.sahrul.moviecatalogue.utils.Consts.BASE_IMAGE_URL
import com.sahrul.moviecatalogue.utils.roundOffDecimal

class MovieAdapter(
    private val tabId: Int
) :
    PagedListAdapter<MovieEntity, MovieAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ComposeView(parent.context)
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ViewHolder(private val composeView: ComposeView) :
        RecyclerView.ViewHolder(composeView) {
        fun bind(movie: MovieEntity) {
            val rating = movie.ratings
            composeView.setContent {
                ItemCard(
                    image = rememberAsyncImagePainter(BASE_IMAGE_URL + movie.image),
                    rating = if (rating.toString().length > 3) {
                        rating.roundOffDecimal().toString()
                    } else rating.toString(),
                    onClick = { onClickItem(movie, composeView.context) }
                )
            }
        }
    }

    private fun onClickItem(movie: MovieEntity, context: Context) {
        val intentDetail = Intent(context, DetailActivity::class.java).apply {
            putExtra(EXTRA_ID, movie.id)
            putExtra(EXTRA_TAB, tabId)
        }
        context.startActivity(intentDetail)
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