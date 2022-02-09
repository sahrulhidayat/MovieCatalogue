package com.sahrul.moviecatalogue.ui.home.tvshow

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.sahrul.moviecatalogue.R
import com.sahrul.moviecatalogue.data.source.local.entity.TvShowEntity
import com.sahrul.moviecatalogue.databinding.FragmentTvShowBinding
import com.sahrul.moviecatalogue.ui.home.HomeViewModel
import com.sahrul.moviecatalogue.utils.SortUtils
import com.sahrul.moviecatalogue.vo.Resource
import com.sahrul.moviecatalogue.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {
    private var _fragmentTvShowBinding: FragmentTvShowBinding? = null
    private val binding get() = _fragmentTvShowBinding

    private val listAdapter by lazy { TvShowAdapter(TAB_TV_SHOW) }
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
        _fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            binding?.rvTvShow?.apply {
                layoutManager = GridLayoutManager(context, 3)
                setHasFixedSize(true)
                adapter = listAdapter
            }

            viewModel.getTvShows(SortUtils.NEWEST).observe(requireActivity(), tvShowsObserver)

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
        viewModel.getTvShows(sort).observe(requireActivity(), tvShowsObserver)
        item.isChecked = true
        return super.onOptionsItemSelected(item)
    }

    private val tvShowsObserver = Observer<Resource<PagedList<TvShowEntity>>> { tvShow ->
        when (tvShow.status) {
            Status.LOADING -> showLoading(true)
            Status.SUCCESS -> {
                showLoading(false)
                showErrorAnim(false)
                listAdapter.submitList(tvShow.data)
            }
            Status.ERROR -> {
                showLoading(false)
                showErrorAnim(true)
                Toast.makeText(context, tvShow.message, Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }

    private fun showLoading(isLoading: Boolean) {
        binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showErrorAnim(isEmpty: Boolean) {
        if (isEmpty)
            binding?.apply {
                rvTvShow.visibility = View.GONE
                animError.visibility = View.VISIBLE
            }
        else
            binding?.apply {
                rvTvShow.visibility = View.VISIBLE
                animError.visibility = View.GONE
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentTvShowBinding = null
    }

    companion object {
        const val TAB_TV_SHOW = 1
    }
}