package com.saathiral.removechinaapps.ui.adapter

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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
            .inflate(R.layout.recyclerview_gridview, parent, false)
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
            Toast.makeText(activity, "System apps can't be uninstalled !!!", Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return appInfoArrayList.size
    }

    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var card_image: ImageView
        var uninstall_icon: ImageView
        var app_name: TextView

        init {
            card_image = itemView.findViewById(R.id.card_image)
            uninstall_icon = itemView.findViewById(R.id.uninstall_icon)
            app_name = itemView.findViewById(R.id.app_name)
        }
    }

    init {
        this.appInfoArrayList = appInfoArrayList
    }
}
