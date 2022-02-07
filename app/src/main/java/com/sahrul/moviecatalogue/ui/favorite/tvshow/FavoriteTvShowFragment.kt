package com.sahrul.moviecatalogue.ui.favorite.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sahrul.moviecatalogue.databinding.FragmentFavoriteTvShowBinding
import com.sahrul.moviecatalogue.ui.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteTvShowFragment : Fragment() {
    private var _fragmentFavoriteTvShowBinding: FragmentFavoriteTvShowBinding? = null
    private val binding get() = _fragmentFavoriteTvShowBinding

    private val listAdapter by lazy { FavoriteTvShowAdapter(TAB_TV_SHOW) }
    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentFavoriteTvShowBinding =
            FragmentFavoriteTvShowBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.rvFavoriteTvShow?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = listAdapter
        }

        if (activity != null) {
            showLoading(true)
            viewModel.getFavoriteTvShows().observe(requireActivity()) { favoriteTvShows ->
                showLoading(false)
                listAdapter.submitList(favoriteTvShows)

                if (favoriteTvShows.isEmpty())
                    showEmptyAnim(true)
                else
                    showEmptyAnim(false)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showEmptyAnim(isEmpty: Boolean) {
        if (isEmpty)
            binding?.apply {
                rvFavoriteTvShow.visibility = View.GONE
                animDataEmpty.visibility = View.VISIBLE
            }
        else
            binding?.apply {
                rvFavoriteTvShow.visibility = View.VISIBLE
                animDataEmpty.visibility = View.GONE
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentFavoriteTvShowBinding = null
    }

    companion object {
        const val TAB_TV_SHOW = 1
    }
}