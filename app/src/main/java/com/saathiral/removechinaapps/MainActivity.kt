package com.saathiral.removechinaapps

import android.R.attr
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.saathiral.removechinaapps.ui.home.HomeFragment
import com.saathiral.removechinaapps.ui.slideshow.SlideshowFragment


class MainActivity : AppCompatActivity() {
    private var drawerLayout : DrawerLayout? = null
    private var menu_image : ImageButton? = null
    private var application_name : TextView? = null
    private var search_image : ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialViews()
        initialSetupViews()

    }

    private fun initialViews() {
        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout) as DrawerLayout
        menu_image = findViewById<ImageButton>(R.id.menu_image) as ImageButton
        application_name = findViewById<TextView>(R.id.application_name) as TextView
        search_image = findViewById<ImageButton>(R.id.search_image) as ImageButton
        menu_image!!.setOnClickListener { drawerLayout!!.openDrawer(Gravity.LEFT) }
        application_name!!.typeface = Typeface.createFromAsset(assets, "Exo2-Medium.ttf")

    }

    private fun initialSetupViews() {
        supportFragmentManager.beginTransaction().add(R.id.fragment, HomeFragment(this)).commit()
        supportFragmentManager.beginTransaction().add(R.id.navigation_fragment, SlideshowFragment()).commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        for (fragment in supportFragmentManager.fragments) {
            fragment.onActivityResult(requestCode, resultCode, data)
        }
    }
}