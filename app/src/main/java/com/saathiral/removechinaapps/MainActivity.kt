package com.saathiral.removechinaapps

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.saathiral.removechinaapps.sharedpreferences.StorePreferences
import com.saathiral.removechinaapps.ui.home.HomeFragment
import com.saathiral.removechinaapps.ui.slideshow.SlideshowFragment


class MainActivity : AppCompatActivity() {
    private var drawerLayout : DrawerLayout? = null
    private var menu_image : ImageButton? = null
    private var application_name : TextView? = null
    private var sort_image : ImageButton? = null
    private var homeFragment : HomeFragment?= null
    private var storePreferences : StorePreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialViews()
        initialSetupViews()

    }

    private fun initialViews() {
        storePreferences = StorePreferences(this)
        homeFragment = HomeFragment(this)
        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout) as DrawerLayout
        menu_image = findViewById<ImageButton>(R.id.menu_image) as ImageButton
        application_name = findViewById<TextView>(R.id.application_name) as TextView
        menu_image!!.setOnClickListener { drawerLayout!!.openDrawer(Gravity.LEFT) }
        //application_name!!.typeface = Typeface.createFromAsset(assets, "Exo2-Medium.ttf")
        sort_image = findViewById<ImageButton>(R.id.sort_image) as ImageButton
        sort_image!!.setOnClickListener {
            val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
            val inflater: LayoutInflater = layoutInflater
            val view = inflater.inflate(R.layout.sorting_details, null)
            dialogBuilder.setView(view)
            var atozCheckBox = view.findViewById<CheckBox>(R.id.atoz_checkbox)
            var ztoaCheckBox = view.findViewById<CheckBox>(R.id.ztoa_checkbox)
            if(storePreferences!!.getSorting().equals("AtoZ", ignoreCase = true)) {
                atozCheckBox.isChecked = true
                ztoaCheckBox.isChecked = false
            } else {
                atozCheckBox.isChecked = false
                ztoaCheckBox.isChecked = true
            }
            atozCheckBox.setOnClickListener {
                if(atozCheckBox.isChecked) {
                    atozCheckBox.isChecked = true
                    ztoaCheckBox.isChecked = false
                    storePreferences!!.setSorting("AtoZ")
                    applySorting("AtoZ")
                } else {
                    atozCheckBox.isChecked = true
                    ztoaCheckBox.isChecked = false
                    storePreferences!!.setSorting("AtoZ")
                }
            }
            ztoaCheckBox.setOnClickListener {
                if(ztoaCheckBox.isChecked) {
                    atozCheckBox.isChecked = false
                    ztoaCheckBox.isChecked = true
                    storePreferences!!.setSorting("ZtoA")
                    applySorting("ZtoA")
                } else {
                    atozCheckBox.isChecked = false
                    ztoaCheckBox.isChecked = true
                    storePreferences!!.setSorting("ZtoA")
                }
            }
            val alertDialog: AlertDialog = dialogBuilder.create()
            alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            alertDialog.show()
        }
    }

    private fun applySorting(whichSorting: String?) {
        when (whichSorting) {
            "AtoZ" -> {
                homeFragment!!.forSorting("AtoZ")
            }
            "ZtoA" -> {
                homeFragment!!.forSorting("ZtoA")
            }
            else -> {
                homeFragment!!.forSorting("AtoZ")
            }
        }
    }

    private fun initialSetupViews() {
        supportFragmentManager.beginTransaction().add(R.id.fragment, homeFragment!!).commit()
        supportFragmentManager.beginTransaction().add(R.id.navigation_fragment, SlideshowFragment()).commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        for (fragment in supportFragmentManager.fragments) {
            fragment.onActivityResult(requestCode, resultCode, data)
        }
    }
}