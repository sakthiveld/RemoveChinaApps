package com.saathiral.removechinaapps.ui.userapps

import android.app.Activity
import android.app.Activity.*
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.saathiral.removechinaapps.AppInfo
import com.saathiral.removechinaapps.R
import com.saathiral.removechinaapps.ui.adapter.RecyclerViewAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import java.util.function.Predicate

class UserAppsFragment(var activity: Activity) : Fragment() {
    private var progressBar: ProgressBar? = null
    private var recyclerView: RecyclerView? = null
    private var adView: AdView? = null
    private var adRequest: AdRequest? = null
    private var recyclerViewAdapter: RecyclerViewAdapter? = null
    private var swipe_refresh_layout: SwipeRefreshLayout? = null
    val userApps: MutableList<AppInfo> = mutableListOf()
    val systemApps: MutableList<AppInfo> = mutableListOf()
    var appInfo: MutableList<AppInfo> = mutableListOf()

    companion object {
        fun newInstance() = UserAppsFragment(activity = Activity())
    }

    private lateinit var viewModel: UserAppsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.user_apps_fragment, container, false)
        progressBar = view.findViewById<ProgressBar>(R.id.progressBar) as ProgressBar
        recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        adView = view.findViewById<AdView>(R.id.adView) as AdView
        swipe_refresh_layout = view.findViewById(R.id.swipe_refresh_layout) as SwipeRefreshLayout
        swipe_refresh_layout!!.setOnRefreshListener {
            CoroutineScope(Dispatchers.Main).launch {
                recyclerView!!.visibility = View.INVISIBLE
                appInfo.clear()
                appInfo = getPackageDetails()
                recyclerViewAdapter!!.notifyDataSetChanged()
                recyclerView!!.visibility = View.VISIBLE
                swipe_refresh_layout!!.isRefreshing = false
            }
        }
        initialAdsView()
        getApplicationPackageData()
        return view
    }

    private fun initialAdsView() {
        MobileAds.initialize(activity) { }
        adRequest = AdRequest.Builder().build() as AdRequest
        adView!!.loadAd(adRequest)
        adView!!.adListener = object : AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                // Code to be executed when an ad request fails.
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            override fun onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        }
    }

    private fun getApplicationPackageData() {
        CoroutineScope(Dispatchers.Main).launch {
            progressBar!!.visibility = View.VISIBLE
            appInfo = getPackageDetails()
            recyclerViewAdapter = RecyclerViewAdapter(activity, appInfo)
            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
            recyclerView!!.layoutManager = layoutManager
            recyclerView!!.isNestedScrollingEnabled = false
            recyclerView!!.invalidate()
            recyclerView!!.adapter = recyclerViewAdapter
            recyclerViewAdapter!!.notifyDataSetChanged()
            progressBar!!.visibility = View.GONE
        }
    }

    suspend fun getPackageDetails() = Dispatchers.Default {

        val deviceAllApps: ArrayList<PackageInfo> =
            requireActivity().packageManager.getInstalledPackages(0) as ArrayList<PackageInfo>

        for (i in 0 until deviceAllApps.size) {
            if (deviceAllApps.get(i).applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM === 0) {
                // 1. Applications downloaded from Google Play Store
                val newInfo = AppInfo()
                newInfo.appName =
                    deviceAllApps.get(i).applicationInfo.loadLabel(requireActivity().packageManager)
                        .toString()
                newInfo.packageName = deviceAllApps.get(i).packageName
                newInfo.versionName = deviceAllApps.get(i).versionName
                newInfo.versionCode = deviceAllApps.get(i).versionCode
                newInfo.appIcon =
                    deviceAllApps.get(i).applicationInfo.loadIcon(requireActivity().packageManager)
                userApps.add(newInfo)
            }
            if (deviceAllApps[i].applicationInfo.flags and ApplicationInfo.FLAG_UPDATED_SYSTEM_APP !== 0) {
                // 2. Applications preloaded in device by manufecturer
                val newInfo = AppInfo()
                newInfo.appName =
                    deviceAllApps.get(i).applicationInfo.loadLabel(requireActivity().packageManager)
                        .toString()
                newInfo.packageName = deviceAllApps.get(i).packageName
                newInfo.versionName = deviceAllApps.get(i).versionName
                newInfo.versionCode = deviceAllApps.get(i).versionCode
                newInfo.appIcon =
                    deviceAllApps.get(i).applicationInfo.loadIcon(requireActivity().packageManager)
                systemApps.add(newInfo)
            }
        }
        return@Default userApps
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserAppsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode === RESULT_OK) {
            userApps!!.removeAt(requestCode)
            recyclerViewAdapter!!.notifyDataSetChanged()
            Toast.makeText(
                context,
                "You are awesome !!! The App has been uninstalled",
                Toast.LENGTH_LONG
            ).show()
        } else if (resultCode === RESULT_CANCELED) {
            //Toast.makeText(context, "onActivityResult: user canceled the (un)install", Toast.LENGTH_SHORT).show();
        } else if (resultCode === RESULT_FIRST_USER) {
            //Toast.makeText(context, "onActivityResult: failed to (un)install", Toast.LENGTH_SHORT).show();
        }
    }
}