package com.sahrul.moviecatalogue.ui.favorite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sahrul.moviecatalogue.databinding.FragmentFavoriteMovieBinding
import com.sahrul.moviecatalogue.ui.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteMovieFragment : Fragment() {
    private var _fragmentFavoriteMovieBinding: FragmentFavoriteMovieBinding? = null
    private val binding get() = _fragmentFavoriteMovieBinding

    private val listAdapter by lazy { FavoriteMovieAdapter(TAB_MOVIE) }
    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentFavoriteMovieBinding =
            FragmentFavoriteMovieBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.rvFavoriteMovie?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = listAdapter
        }

        if (activity != null) {
            showLoading(true)
            viewModel.getFavoriteMovies().observe(requireActivity(), { favoriteMovies ->
                showLoading(false)
                listAdapter.submitList(favoriteMovies)

                if (favoriteMovies.isEmpty())
                    showEmptyAnim(true)
                else
                    showEmptyAnim(false)
            })
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showEmptyAnim(isEmpty: Boolean) {
        if (isEmpty)
            binding?.apply {
                rvFavoriteMovie.visibility = View.GONE
                animDataEmpty.visibility = View.VISIBLE
            }
        else
            binding?.apply {
                rvFavoriteMovie.visibility = View.VISIBLE
                animDataEmpty.visibility = View.GONE
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentFavoriteMovieBinding = null
    }

    companion object {
        const val TAB_MOVIE = 0
    }
}