package ua.com.bonanzasweetadventures

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BonanzaViewModel : ViewModel() {

    private val _liveSatus: MutableLiveData<String> = MutableLiveData<String>(Constants.CLEAR)
    val liveStatus: LiveData<String> = _liveSatus

    fun putDataToLiveStatus(dataToLiveStatus: String){
        Log.d("123123", "method putDataToLiveStatus is runing dataToLiveStatus is $dataToLiveStatus")
        _liveSatus.value = dataToLiveStatus
    }
}