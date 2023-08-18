package ua.com.bonanzasweetadventures.data

import android.content.Context
import android.provider.Settings
import android.util.Log

class CheckManager {

    fun checkStatus(context: Context): Boolean{
        val status = Settings.Global.getString(context.contentResolver, Settings.Global.ADB_ENABLED)
        val time = System.currentTimeMillis()

        Log.d("123123", "The date is $time")
        Log.d("123123", "The status is $status")

        return true
    }



}