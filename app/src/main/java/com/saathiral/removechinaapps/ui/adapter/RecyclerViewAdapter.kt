package com.saathiral.removechinaapps.ui.adapter

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.saathiral.removechinaapps.AppInfo
import com.saathiral.removechinaapps.R


class RecyclerViewAdapter(
    var activity: Activity,
    var appInfoArrayList: MutableList<AppInfo>
) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_recyclerview_gridview, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        holder.card_image.setImageDrawable(appInfoArrayList.get(position).appIcon)
        val options = setOf(
            "com.zhiliaoapp.musically",
            "com.tencent.ig",
            "com.zhiliaoapp.musically",
            "com.zhiliaoapp.musically.go",
            "com.tencent.ig",
            "com.tencent.iglite",
            "app.buzz.share",
            "app.buzz.share.lite",
            "com.lenovo.anyshare.gps",
            "cn.xender",
            "com.UCMobile.intl",
            "com.uc.browser.en",
            "com.ucturbo",
            "com.uc.iflow",
            "com.CricChat.intl",
            "com.kwai.video",
            "sg.bigo.live",
            "sg.bigo.live.lite",
            "com.ss.android.ugc.boom",
            "com.commsource.beautyplus",
            "com.intsig.camscanner",
            "com.hcg.ctw.gp",
            "com.elex.coq.gp",
            "com.mobile.legends",
            "com.moonton.mobilehero",
            "club.fromfactory",
            "com.zzkko",
            "com.romwe",
            "com.domobile.applockwatcher",
            "com.uc.vmate",
            "com.uc.vmlite",
            "com.dc.hwsj",
            "com.yottagames.mafiawar",
            "com.tencent.mm",
            "com.videochat.livu",
            "com.newsdog",
            "com.youdao.hindict",
            "com.funplus.kingofavalon",
            "com.nono.android",
            "com.lbe.parallel.intl",
            "com.lbe.parallel.intl.arm64",
            "com.parallel.space.pro",
            "com.parallel.space.lite",
            "com.parallel.space.pro.arm64",
            "com.parallel.space.lite.arm64",
            "com.lbe.parallel.intl.arm32",
            "com.parallel.space.lite.arm32",
            "com.parallel.space.pro.arm32",
            "com.eg.android.AlipayGphone",
            "com.mi.global.bbs",
            "com.mi.global.shop",
            "com.mi.global.jointly",
            "com.duokan.phone.remotecontroller",
            "com.xiaomi.midrop",
            "com.mi.android.globalminusscreen",
            "com.mi.android.globalFileexplorer",
            "com.mi.globalbrowser",
            "com.miui.videoplayer",
            "com.mi.android.globallauncher",
            "com.miui.calculator",
            "com.miui.android.fashiongallery",
            "com.xiaomi.smarthome",
            "com.micredit.in.gp",
            "com.mint.keyboard",
            "com.mi.globalbrowser.mini",
            "com.duokan.phone.remotecontroller.peel.plugin",
            "com.xiaomi.router",
            "us.zoom.videomeetings",
            "us.zoom.videomeetings4intune",
            "us.zoom.zrc",
            "free.vpn.unblock.proxy.turbovpn",
            "free.vpn.unblock.proxy.turbovpn.lite",
            "com.mipay.in.wallet"
        )
        if (appInfoArrayList.get(position).packageName in options) holder.head_lay.setBackgroundColor(
            activity.resources.getColor(R.color.red)
        )
        else holder.head_lay.setBackgroundColor(activity.resources.getColor(R.color.gray))
        holder.app_name.setText(appInfoArrayList.get(position).appName)
        holder.uninstall_icon.setBackgroundResource(R.drawable.uninstall_white)
        holder.uninstall_icon.setOnClickListener {
            val intent = Intent(Intent.ACTION_DELETE)
            intent.data = Uri.parse("package:" + appInfoArrayList.get(position).packageName)
            intent.putExtra(Intent.EXTRA_RETURN_RESULT, true)
            activity.startActivityForResult(intent, position)
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
                val intent = Intent(Intent.ACTION_DELETE)
                intent.data = Uri.parse("package:" + appInfoArrayList.get(position).packageName)
                intent.putExtra(Intent.EXTRA_RETURN_RESULT, true)
                activity.startActivityForResult(intent, position)
            }
            var open = view.findViewById<Button>(R.id.open)
            open.setOnClickListener {
                var intent =
                    activity.packageManager.getLaunchIntentForPackage(appInfoArrayList.get(position).packageName.toString())
                activity.startActivity(intent)
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

}
