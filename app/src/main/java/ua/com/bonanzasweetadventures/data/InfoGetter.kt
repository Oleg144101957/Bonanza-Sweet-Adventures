package ua.com.bonanzasweetadventures.data

import android.content.Context
import android.provider.Settings
import android.util.Log
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.facebook.applinks.AppLinkData
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.onesignal.OneSignal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ua.com.bonanzasweetadventures.BonanzaViewModel
import ua.com.bonanzasweetadventures.Constants
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class InfoGetter(private val context: Context) {

    private val linkStorage = LinkStorage(context)
    suspend fun prepearDestination(viewModel: BonanzaViewModel){
        //check time and adb

        Log.d("123123", "Begin method prepearDestination")

        val checkManager = CheckManager()
        val status = checkManager.checkStatus(context = context)

        val apps = getApps()
        val fb = getFacebook()
        val gaid = getGaid()

        Log.d("123123", "apps is $apps")
        Log.d("123123", "fb is $fb")
        Log.d("123123", "gaid is $gaid")

        if (status){
            buildLink(apps, fb, gaid, viewModel)
        }
    }

    private fun buildLink(
        apps: MutableMap<String, Any>?,
        fb: String,
        gaid: String,
        bonanzaViewModel: BonanzaViewModel
    ){
        OneSignal.setExternalUserId(gaid)

        val listOfFacebookSubs : List<String> = fb.substringAfter("://")
            .split('_')
            .filter { it.startsWith("sub") }
            .map { it.substringAfter("=") }

        val af_channel: String = apps?.getOrDefault(Constants.AF_CHANNEL, "null").toString()
        val adset: String = apps?.getOrDefault(Constants.ADSET, "null").toString()
        val media_source: String = apps?.getOrDefault(Constants.MEDIA_SOURCE, "null").toString()
        val af_status: String = apps?.getOrDefault(Constants.AF_STATUS, "null").toString()
        val af_ad: String = apps?.getOrDefault(Constants.AF_AD, "null").toString()
        val campaign_id: String = apps?.getOrDefault(Constants.CAMPAIGN_ID, "null").toString()
        val adset_id: String = apps?.getOrDefault(Constants.ADSET_ID, "null").toString()
        val ad_id: String = apps?.getOrDefault(Constants.AD_ID, "null").toString()

        val sub1 = listOfFacebookSubs.getOrNull(0) ?: "null"
        OneSignal.sendTag("sub1", sub1)

        val status = Settings.Global.getString(context.contentResolver, Settings.Global.ADB_ENABLED)


        if (af_status == "Organic" && status == "1"){
            val sub2 = if (listOfFacebookSubs.getOrNull(1) != null) listOfFacebookSubs.getOrNull(1) else "LGxfTPfW"

            val listOfBaseEssentials = listOf("https://f", "t-app", "s.com/")
            val ft = listOfBaseEssentials[0]+listOfBaseEssentials[1]+listOfBaseEssentials[2]

            val linkBuilder = StringBuilder("$ft$sub2?")

            linkBuilder.append("af_channel=$af_channel&")
            linkBuilder.append("adset=$adset&")
            linkBuilder.append("media_source=$media_source&")
            linkBuilder.append("af_status=$af_status&")
            linkBuilder.append("af_ad=$af_ad&")
            linkBuilder.append("campaign_id=$campaign_id&")
            linkBuilder.append("adset_id=$adset_id&")
            linkBuilder.append("ad_id=$ad_id&")
            linkBuilder.append("sub3=${listOfFacebookSubs.getOrNull(2)}")
            linkBuilder.append("sub4=${listOfFacebookSubs.getOrNull(3)}")
            linkBuilder.append("sub5=${listOfFacebookSubs.getOrNull(4)}")
            linkBuilder.append("sub6=${listOfFacebookSubs.getOrNull(5)}")
            linkBuilder.append("sub7=${listOfFacebookSubs.getOrNull(6)}")
            linkBuilder.append("sub8=${listOfFacebookSubs.getOrNull(7)}")
            linkBuilder.append("sub9=${listOfFacebookSubs.getOrNull(8)}")
            linkBuilder.append("sub10=${listOfFacebookSubs.getOrNull(9)}")

            linkStorage.saveLink(linkBuilder.toString())

            bonanzaViewModel.putDataToLiveStatus("Ready")
        } else {
            bonanzaViewModel.putDataToLiveStatus(Constants.ATTENTION)

        }
    }

    fun readLink(): String{
        return linkStorage.getLink()
    }

    fun uploadLink(dataToSave: String){
        linkStorage.saveLink(dataToSave)
    }

    private suspend fun getApps(): MutableMap<String, Any>? = suspendCoroutine { continuation ->
        AppsFlyerLib.getInstance()
            .init(Constants.APPS_DEV_KEY, CustomListener{
                  continuation.resume(it)
           }, context).start(context)
    }

    private suspend fun getFacebook() : String = suspendCoroutine { continuation ->
        AppLinkData.fetchDeferredAppLinkData(context){
            continuation.resume(it?.targetUri.toString())
        }
    }

    private suspend fun getGaid() : String = withContext(Dispatchers.IO){
        AdvertisingIdClient.getAdvertisingIdInfo(context).id.toString()
    }
}

class CustomListener(private val onReceiveValue : (MutableMap<String, Any>?) -> Unit) :
    AppsFlyerConversionListener {
    override fun onConversionDataSuccess(p0: MutableMap<String, Any>?) {
        onReceiveValue(p0)
    }

    override fun onConversionDataFail(p0: String?) {
        onReceiveValue(null)
    }

    override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {
        onReceiveValue(null)
    }

    override fun onAttributionFailure(p0: String?) {
        onReceiveValue(null)
    }
}