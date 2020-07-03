package com.saathiral.removechinaapps.ui.home

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.saathiral.removechinaapps.R
import com.saathiral.removechinaapps.ui.adapter.SectionsPagerAdapter

class HomeFragment(var activity: Activity) : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
        })
        val sectionsPagerAdapter = activity?.let {
            SectionsPagerAdapter(it, requireActivity().supportFragmentManager)
        }
        val viewPager: ViewPager = root.findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tab_layout: TabLayout = root.findViewById(R.id.tab_layout)
        tab_layout.setupWithViewPager(viewPager)

        return root
    }
}