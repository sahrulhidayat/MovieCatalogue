package com.sahrul.moviecatalogue.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.sahrul.moviecatalogue.R
import com.sahrul.moviecatalogue.data.source.local.entity.MovieEntity
import com.sahrul.moviecatalogue.data.source.local.entity.TvShowEntity
import com.sahrul.moviecatalogue.databinding.ActivityDetailBinding
import com.sahrul.moviecatalogue.ui.home.movie.MovieFragment.Companion.TAB_MOVIE
import com.sahrul.moviecatalogue.ui.home.tvshow.TvShowFragment.Companion.TAB_TV_SHOW
import com.sahrul.moviecatalogue.utils.Consts.BASE_IMAGE_URL
import com.sahrul.moviecatalogue.utils.loadImage
import com.sahrul.moviecatalogue.vo.Resource
import com.sahrul.moviecatalogue.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private var _activityDetailBinding: ActivityDetailBinding? = null
    private val binding get() = _activityDetailBinding

    private val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _activityDetailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(_activityDetailBinding?.root)

        setSupportActionBar(binding?.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val id = intent.getIntExtra(EXTRA_ID, -1)

        when (intent.getIntExtra(EXTRA_TAB, 0)) {
            TAB_MOVIE -> {
                supportActionBar?.title = resources.getText(R.string.movie_details)
                viewModel.getMovieDetails(id).observe(this) { movie ->
                    populateMovie(movie)
                    binding?.fabFavorite?.setOnClickListener {
                        movie.data?.let { movie ->
                            viewModel.setFavoriteMovie(
                                movie
                            )
                        }
                    }
                }
            }
            TAB_TV_SHOW -> {
                supportActionBar?.title = resources.getText(R.string.tv_show_details)
                viewModel.getTvShowDetails(id).observe(this) { tvShow ->
                    populateTvShow(tvShow)
                    binding?.fabFavorite?.setOnClickListener {
                        tvShow.data?.let { tvShow ->
                            viewModel.setFavoriteTvShow(
                                tvShow
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun populateMovie(movie: Resource<MovieEntity>) {
        when (movie.status) {
            Status.LOADING -> showLoading(true)
            Status.SUCCESS -> {
                showLoading(false)
                movie.data?.let {
                    binding?.apply {
                        with(movie.data) {
                            loadImage(BASE_IMAGE_URL + image, imgPoster)
                            tvTitle.text = title
                            tvRatings.text = ratings.toString()
                            tvCategory.text = category
                            tvReleaseDate.text = release
                            tvOverview.text = overview
                            tvDuration.text = duration
                        }
                    }
                    setFavoriteButton(movie.data.isFavorite)
                }
            }
            Status.ERROR -> {
                showLoading(false)
                Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun populateTvShow(tvShow: Resource<TvShowEntity>) {
        when (tvShow.status) {
            Status.LOADING -> showLoading(true)
            Status.SUCCESS -> {
                showLoading(false)
                tvShow.data?.let {
                    binding?.apply {
                        with(tvShow.data) {
                            loadImage(BASE_IMAGE_URL + image, imgPoster)
                            tvTitle.text = title
                            tvRatings.text = ratings.toString()
                            tvCategory.text = category
                            tvReleaseDate.text = release
                            tvOverview.text = overview
                        }
                    }
                    setFavoriteButton(tvShow.data.isFavorite)
                }
            }

            Status.ERROR -> {
                showLoading(false)
                Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setFavoriteButton(isFavorite: Boolean) {
        if (isFavorite) {
            binding?.fabFavorite?.setImageDrawable(
                ContextCompat.getDrawable(
                    this, R.drawable.ic_favorite
                )
            )
        } else {
            binding?.fabFavorite?.setImageDrawable(
                ContextCompat.getDrawable(
                    this, R.drawable.ic_favorite_border
                )
            )
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.apply {
            if (isLoading) {
                nestedScrollView.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
                nestedScrollView.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _activityDetailBinding = null
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TAB = "extra_tab"
    }
}