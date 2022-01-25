package com.sahrul.moviecatalogue.ui.home

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sahrul.moviecatalogue.ui.home.movie.MovieFragment
import com.sahrul.moviecatalogue.ui.home.tvshow.TvShowFragment

class HomePagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = MovieFragment()
            1 -> fragment = TvShowFragment()
            else -> Fragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int = 2

}
