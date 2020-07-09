package com.saathiral.removechinaapps.ui.adapter

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.saathiral.removechinaapps.AppInfo
import com.saathiral.removechinaapps.R
import java.util.ArrayList

class SystemAppsRecyclerViewAdapter(
    var activity: Activity,
    appInfoArrayList: MutableList<AppInfo>
) :
    RecyclerView.Adapter<SystemAppsRecyclerViewAdapter.MyViewHolder>() {
    var appInfoArrayList: MutableList<AppInfo>
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.system_recyclerview_gridview, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        holder.card_image.setImageDrawable(appInfoArrayList[position].appIcon)
        holder.app_name.setText(appInfoArrayList[position].appName)
        holder.uninstall_icon.setBackgroundResource(R.drawable.non_uninstall_white)
        holder.uninstall_icon.setOnClickListener {
            //val intent = Intent(Intent.ACTION_DELETE)
            //intent.data = Uri.parse("package:" + appInfoArrayList.get(position).packageName)
            //intent.putExtra(Intent.EXTRA_RETURN_RESULT, true)
            //activity.startActivityForResult(intent, position)
            Toast.makeText(activity, "System apps can't be uninstalled !!!", Toast.LENGTH_LONG).show()
        }
        holder.googleplay_icon.setBackgroundResource(R.drawable.playstore)
        holder.googleplay_icon.setOnClickListener {
            val uri = Uri.parse(
                "https://play.google.com/store/apps/details?id=" + appInfoArrayList.get(position).packageName.toString() + "&hl=en"
            )
            val intent = Intent(Intent.ACTION_VIEW, uri)
            activity.startActivity(intent)
        }
        holder.more_icon.setBackgroundResource(R.drawable.more_white)
        holder.more_icon.setOnClickListener {
            val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(activity)
            val inflater: LayoutInflater = activity.layoutInflater
            val view = inflater.inflate(R.layout.app_details, null)
            dialogBuilder.setView(view)
            var card_image = view.findViewById<ImageView>(R.id.card_image)
            card_image.setImageDrawable(appInfoArrayList.get(position).appIcon)
            var app_name = view.findViewById<TextView>(R.id.app_name)
            app_name.setText(appInfoArrayList.get(position).appName)
            var uninstall = view.findViewById<Button>(R.id.uninstall)
            uninstall.setOnClickListener {
                //val intent = Intent(Intent.ACTION_DELETE)
                //intent.data = Uri.parse("package:" + appInfoArrayList.get(position).packageName)
                //intent.putExtra(Intent.EXTRA_RETURN_RESULT, true)
                //activity.startActivityForResult(intent, position)
                Toast.makeText(activity, "System apps can't be uninstalled !!!", Toast.LENGTH_LONG).show()
            }
            var open = view.findViewById<Button>(R.id.open)
            open.setOnClickListener {
                var intent =
                    activity.packageManager.getLaunchIntentForPackage(appInfoArrayList.get(position).packageName.toString())
                try {
                    activity.startActivity(intent)
                } catch (e : Exception) {
                    Toast.makeText(activity, "This app won't open !!!", Toast.LENGTH_LONG).show()
                }
            }
            var details_icon = view.findViewById<ImageView>(R.id.details_icon)
            details_icon.setImageDrawable(activity.resources.getDrawable(R.drawable.details_white))
            var googleplay_icon = view.findViewById<ImageView>(R.id.googleplay_icon)
            googleplay_icon.setImageDrawable(activity.resources.getDrawable(R.drawable.playstore))
            var third_lay = view.findViewById<RelativeLayout>(R.id.third_lay)
            third_lay.setOnClickListener {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts(
                    "package",
                    appInfoArrayList.get(position).packageName.toString(),
                    null
                )
                intent.data = uri
                activity.startActivity(intent)
            }
            var fourth_lay = view.findViewById<RelativeLayout>(R.id.fourth_lay)
            fourth_lay.setOnClickListener {
                val uri = Uri.parse(
                    "https://play.google.com/store/apps/details?id=" + appInfoArrayList.get(position).packageName.toString() + "&hl=en"
                )
                val intent = Intent(Intent.ACTION_VIEW, uri)
                activity.startActivity(intent)
            }
            val alertDialog: AlertDialog = dialogBuilder.create()
            alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            alertDialog.show()
        }
    }

    override fun getItemCount(): Int {
        return appInfoArrayList.size
    }

    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var card_image: ImageView
        var uninstall_icon: ImageView
        var googleplay_icon: ImageView
        var more_icon: ImageView
        var app_name: TextView
        var head_lay: ConstraintLayout

        init {
            card_image = itemView.findViewById(R.id.card_image)
            uninstall_icon = itemView.findViewById(R.id.uninstall_icon)
            googleplay_icon = itemView.findViewById(R.id.googleplay_icon)
            more_icon = itemView.findViewById(R.id.more_icon)
            app_name = itemView.findViewById(R.id.app_name)
            head_lay = itemView.findViewById(R.id.head_lay)
        }
    }

    init {
        this.appInfoArrayList = appInfoArrayList
    }
}
