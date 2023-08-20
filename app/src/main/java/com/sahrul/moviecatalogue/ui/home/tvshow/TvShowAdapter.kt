package com.sahrul.moviecatalogue.ui.home.tvshow

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sahrul.moviecatalogue.data.source.local.entity.TvShowEntity
import com.sahrul.moviecatalogue.databinding.ItemsListBinding
import com.sahrul.moviecatalogue.ui.detail.DetailActivity
import com.sahrul.moviecatalogue.utils.Consts.BASE_IMAGE_URL
import com.sahrul.moviecatalogue.utils.loadImage
import com.sahrul.moviecatalogue.utils.roundOffDecimal

class TvShowAdapter(private val tabId: Int) :
    PagedListAdapter<TvShowEntity, TvShowAdapter.ViewHolder>(DIFF_CALLBACK) {

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
        fun bind(tvShow: TvShowEntity) {
            val context = itemView.context
            val rating = tvShow.ratings

            with(binding) {
                context.loadImage(BASE_IMAGE_URL + tvShow.image, imgPoster)
                tvRatings.text = if (rating.toString().length > 3) {
                    rating.roundOffDecimal().toString()
                } else tvShow.ratings.toString()
            }

            onClickItem(itemView, tvShow, context)
        }
    }

    private fun onClickItem(view: View, tvShow: TvShowEntity, context: Context) {
        view.setOnClickListener {
            val intentDetail = Intent(context, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_ID, tvShow.id)
                putExtra(DetailActivity.EXTRA_TAB, tabId)
            }
            context.startActivity(intentDetail)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}