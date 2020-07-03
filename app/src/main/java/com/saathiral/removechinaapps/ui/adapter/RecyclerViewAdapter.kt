package com.saathiral.removechinaapps.ui.adapter

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.color.MaterialColors.getColor
import com.saathiral.removechinaapps.AppInfo
import com.saathiral.removechinaapps.R
import kotlinx.android.synthetic.main.recyclerview_gridview.view.*
import java.util.*

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
            .inflate(R.layout.recyclerview_gridview, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        holder.card_image.setImageDrawable(appInfoArrayList.get(position).appIcon)
        val options = setOf("com.zhiliaoapp.musically",
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
            "com.mipay.in.wallet")
        if (appInfoArrayList.get(position).packageName in options) holder.head_lay.setBackgroundColor(activity.resources.getColor(R.color.red))
        else holder.head_lay.setBackgroundColor(activity.resources.getColor(R.color.gray))
        holder.app_name.setText(appInfoArrayList.get(position).appName)
        holder.uninstall_icon.setBackgroundResource(R.drawable.uninstall_white)
        holder.uninstall_icon.setOnClickListener {
            val intent = Intent(Intent.ACTION_DELETE)
            intent.data = Uri.parse("package:" + appInfoArrayList.get(position).packageName)
            intent.putExtra(Intent.EXTRA_RETURN_RESULT, true)
            activity.startActivityForResult(intent, position)
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
        var head_lay: ConstraintLayout

        init {
            card_image = itemView.findViewById(R.id.card_image)
            uninstall_icon = itemView.findViewById(R.id.uninstall_icon)
            app_name = itemView.findViewById(R.id.app_name)
            head_lay = itemView.findViewById(R.id.head_lay)
        }
    }

}
