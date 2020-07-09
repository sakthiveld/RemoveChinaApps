package com.saathiral.removechinaapps.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

class StorePreferences(context: Context) {

    val whichSorting = "whichSorting"
    private val preference = context.getSharedPreferences("AppsRemover", Context.MODE_PRIVATE)

    fun getSorting(): String {
        return preference.getString(whichSorting, "AtoZ").toString()
    }

    fun setSorting(value: String) {
        val editor = preference.edit()
        editor.putString(whichSorting, value)
        editor.apply()
    }
}