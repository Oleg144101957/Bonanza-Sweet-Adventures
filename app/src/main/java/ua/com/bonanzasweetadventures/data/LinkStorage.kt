package ua.com.bonanzasweetadventures.data

import android.content.Context
import ua.com.bonanzasweetadventures.Constants

class LinkStorage(private val context: Context) {

    private val sp = context.getSharedPreferences(Constants.SP, Context.MODE_PRIVATE)

    fun saveLink(linkToSave: String){
        if (!isDangerous()){
            sp.edit().putString(Constants.LINK, linkToSave).apply()
        }
    }

    fun getLink(): String{
        return sp.getString(Constants.LINK, Constants.CLEAR) ?: Constants.CLEAR
    }

    private fun isDangerous() : Boolean {
        val currentLink = sp.getString(Constants.LINK, Constants.CLEAR) ?: Constants.CLEAR
        return currentLink == Constants.ATTENTION
    }
}