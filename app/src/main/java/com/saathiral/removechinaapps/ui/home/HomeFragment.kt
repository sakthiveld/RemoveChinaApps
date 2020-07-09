package com.saathiral.removechinaapps.ui.home

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.saathiral.removechinaapps.R
import com.saathiral.removechinaapps.ui.`interface`.SortingInterface
import com.saathiral.removechinaapps.ui.adapter.SectionsPagerAdapter
import com.saathiral.removechinaapps.ui.systemapps.SystemAppsFragment
import com.saathiral.removechinaapps.ui.userapps.UserAppsFragment

class HomeFragment(var activity: Activity) : Fragment(), SortingInterface {

    private lateinit var homeViewModel: HomeViewModel
    private val userAppsFragment = UserAppsFragment(activity)
    private val systemAppsFragment = SystemAppsFragment(activity)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
        })
        val sectionsPagerAdapter = activity?.let {
            SectionsPagerAdapter(it, requireActivity().supportFragmentManager, userAppsFragment, systemAppsFragment)
        }
        val viewPager: ViewPager = root.findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tab_layout: TabLayout = root.findViewById(R.id.tab_layout)
        tab_layout.setupWithViewPager(viewPager)

        return root
    }

    override fun forSorting(whichSorting: String?) {
        userAppsFragment.forSorting(whichSorting)
        systemAppsFragment.forSorting(whichSorting)
    }
}