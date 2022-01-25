package com.sahrul.moviecatalogue.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sahrul.moviecatalogue.ui.favorite.movie.FavoriteMovieFragment
import com.sahrul.moviecatalogue.ui.favorite.tvshow.FavoriteTvShowFragment

class FavoritePagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FavoriteMovieFragment()
            1 -> fragment = FavoriteTvShowFragment()
            else -> Fragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int = 2
}