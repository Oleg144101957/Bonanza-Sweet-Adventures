package ua.com.bonanzasweetadventures

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BonanzaViewModel : ViewModel() {

    private val _liveSatus: MutableLiveData<String> = MutableLiveData<String>("clear")
    val liveStatus: LiveData<String> = _liveSatus

    fun putDataToLiveStatus(dataToLiveStatus: String){
        _liveSatus.value = dataToLiveStatus
    }
}