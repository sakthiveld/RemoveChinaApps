package com.saathiral.removechinaapps.ui.adapter

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.saathiral.removechinaapps.R
import com.saathiral.removechinaapps.ui.systemapps.SystemAppsFragment
import com.saathiral.removechinaapps.ui.userapps.UserAppsFragment

class SectionsPagerAdapter(private val activity: Activity, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return if (position == 0) UserAppsFragment(activity) else SystemAppsFragment(activity)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return activity.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }

    companion object {
        private val TAB_TITLES = intArrayOf(R.string.tab_text_1, R.string.tab_text_2)
    }

}
