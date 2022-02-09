package com.sahrul.moviecatalogue.ui.home.movie

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.sahrul.moviecatalogue.R
import com.sahrul.moviecatalogue.data.source.local.entity.MovieEntity
import com.sahrul.moviecatalogue.databinding.FragmentMovieBinding
import com.sahrul.moviecatalogue.ui.home.HomeViewModel
import com.sahrul.moviecatalogue.utils.SortUtils
import com.sahrul.moviecatalogue.vo.Resource
import com.sahrul.moviecatalogue.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {
    private var _fragmentMovieBinding: FragmentMovieBinding? = null
    private val binding get() = _fragmentMovieBinding

    private val listAdapter by lazy { MovieAdapter(TAB_MOVIE) }
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            binding?.rvMovie?.apply {
                layoutManager = GridLayoutManager(context, 3)
                setHasFixedSize(true)
                adapter = listAdapter
            }

            viewModel.getMovies(SortUtils.NEWEST).observe(requireActivity(), moviesObserver)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.sort_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var sort = ""
        when (item.itemId) {
            R.id.action_newest -> sort = SortUtils.NEWEST
            R.id.action_oldest -> sort = SortUtils.OLDEST
            R.id.action_ratings -> sort = SortUtils.RATINGS
        }
        viewModel.getMovies(sort).observe(requireActivity(), moviesObserver)
        item.isChecked = true
        return super.onOptionsItemSelected(item)
    }

    private val moviesObserver = Observer<Resource<PagedList<MovieEntity>>> { movies ->
        when (movies.status) {
            Status.LOADING -> showLoading(true)
            Status.SUCCESS -> {
                showLoading(false)
                showErrorAnim(false)
                listAdapter.submitList(movies.data)
            }
            Status.ERROR -> {
                showLoading(false)
                showErrorAnim(true)
                Toast.makeText(context, movies.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showErrorAnim(isEmpty: Boolean) {
        if (isEmpty)
            binding?.apply {
                rvMovie.visibility = View.GONE
                animError.visibility = View.VISIBLE
            }
        else
            binding?.apply {
                rvMovie.visibility = View.VISIBLE
                animError.visibility = View.GONE
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentMovieBinding = null
    }

    companion object {
        const val TAB_MOVIE = 0
    }
}