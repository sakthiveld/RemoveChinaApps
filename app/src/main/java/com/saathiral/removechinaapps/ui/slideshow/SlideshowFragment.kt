package com.saathiral.removechinaapps.ui.slideshow

import android.R.attr
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.saathiral.removechinaapps.R


class SlideshowFragment : Fragment() {

    private var application_name : TextView? = null
    private var connect_us_on : TextView? = null
    private var made_in_india : TextView? = null
    private var facebook : ImageButton? = null
    private var instagram : ImageButton? = null
    private var twitter : ImageButton? = null
    private var share_lay : LinearLayout? = null
    private var rate_lay : LinearLayout? = null
    private var contact_lay : LinearLayout? = null
    private var buy_me_a_coffee : TextView? = null
    private var gpay : ImageButton? = null
    private var upi : ImageButton? = null

    private lateinit var slideshowViewModel: SlideshowViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        slideshowViewModel = ViewModelProviders.of(this).get(SlideshowViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)
        slideshowViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        application_name = root.findViewById<TextView>(R.id.application_name) as TextView
        //application_name!!.typeface = Typeface.createFromAsset(requireActivity().assets, "Exo2-Medium.ttf")
        connect_us_on = root.findViewById<TextView>(R.id.connect_us_on) as TextView
        made_in_india = root.findViewById<TextView>(R.id.made_in_india) as TextView
        facebook = root.findViewById(R.id.facebook) as ImageButton
        instagram = root.findViewById(R.id.instagram) as ImageButton
        twitter = root.findViewById(R.id.twitter) as ImageButton
        facebook!!.setOnClickListener(View.OnClickListener {
            val uri = Uri.parse("https://www.facebook.com/saathiral/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        })
        instagram!!.setOnClickListener(View.OnClickListener {
            val uri = Uri.parse("https://www.instagram.com/saathiral/")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        })
        twitter!!.setOnClickListener(View.OnClickListener {
            val uri = Uri.parse("https://www.twitter.com/saathiral")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        })
        share_lay = root.findViewById(R.id.share_lay) as LinearLayout
        share_lay!!.setOnClickListener(View.OnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.type = "text/plain"
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey I am using AppsRemover to uninstall apps easily. You are awesome. Made in India. " + "https://play.google.com/store/apps/details?id="+activity?.packageName.toString() + "&hl=en")
            activity?.startActivity(Intent.createChooser(sendIntent, "Share via"))
        })
        rate_lay = root.findViewById(R.id.rate_lay) as LinearLayout
        rate_lay!!.setOnClickListener(View.OnClickListener {
            val uri = Uri.parse(
                "https://play.google.com/store/apps/details?id=" + activity?.packageName
                    .toString() + "&hl=en"
            )
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)

        })
        contact_lay = root.findViewById(R.id.contact_lay) as LinearLayout
        contact_lay!!.setOnClickListener(View.OnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("mailto:" + "saathiral@outlook.com")
            )
            intent.putExtra(Intent.EXTRA_SUBJECT, "Saathiral Inc - Report")
            intent.putExtra(Intent.EXTRA_TEXT, "Dear Saathiral Team, \n\n")
            startActivity(intent)
        })
        buy_me_a_coffee = root.findViewById(R.id.buy_me_a_coffee) as TextView
        buy_me_a_coffee!!.typeface = Typeface.createFromAsset(requireActivity().assets, "Exo2-Medium.ttf")
        gpay = root.findViewById(R.id.gpay) as ImageButton
        if(isGPayisInstalledorNot()){
            gpay!!.visibility = View.VISIBLE
        } else {
            gpay!!.visibility = View.GONE
        }
        gpay!!.setOnClickListener(View.OnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.setPackage("com.google.android.apps.nbu.paisa.user")
            intent.data = Uri.parse(getUPIString())
            val chooser = Intent.createChooser(intent, "Pay with...")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                startActivityForResult(chooser, 1, null)
            }
        })
        upi = root.findViewById(R.id.upi) as ImageButton
        upi!!.setOnClickListener(View.OnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse(getUPIString())
            val chooser = Intent.createChooser(intent, "Pay with...")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                startActivityForResult(chooser, 1, null)
            }
        })
        return root
    }

    private fun isGPayisInstalledorNot() : Boolean {
        try {
            requireActivity().packageManager.getApplicationInfo("com.google.android.apps.nbu.paisa.user", 0)
            return true
        } catch (exception : Exception) {
            return false
        }
    }

    private fun getUPIString() : String {
        val time = System.currentTimeMillis() / 1000
        val payeeAddress = "removechinaapps@ybl"
        val payeeName = "Sakthivel"
        val payeeMCC = ""
        val trxnID = time.toString() + "UPI"
        val trxnRefId = time.toString() + "UPI"
        val trxnNote = "Made in India"
        val payeeAmount = ""
        val currencyCode = "INR"
        val refUrl = "https://www.saathiral.com/orderid/" + time.toString() + "UPI"

        val UPI = "upi://pay?pa=" + payeeAddress.toString() + "&pn=" + payeeName
            .toString() + "&mc=" + payeeMCC.toString() + "&tid=" + trxnID.toString() + "&tr=" + trxnRefId
            .toString() + "&tn=" + trxnNote.toString() + "&am=" + payeeAmount.toString() + "&cu=" + currencyCode
            .toString() + "&refUrl=" + refUrl
        return UPI
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}