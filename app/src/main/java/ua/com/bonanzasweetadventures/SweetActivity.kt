package ua.com.bonanzasweetadventures

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.ValueCallback
import android.webkit.WebView
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import ua.com.bonanzasweetadventures.custom.CustomScoresScreen
import ua.com.bonanzasweetadventures.custom.FileChooserInterface
import ua.com.bonanzasweetadventures.data.InfoGetter
import ua.com.bonanzasweetadventures.databinding.ActivitySweetBinding

class SweetActivity : AppCompatActivity() {



    private lateinit var chooseCallback: ValueCallback<Array<Uri?>>
    private val getContent = registerForActivityResult(ActivityResultContracts.GetMultipleContents()){
        chooseCallback.onReceiveValue(it.toTypedArray())
    }
    private lateinit var binding: ActivitySweetBinding
    private lateinit var customScoresScreen: CustomScoresScreen



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySweetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        Log.d("123123", "onCreate in SweetActivity")


        setDisplay()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val bundle = Bundle()
        customScoresScreen.saveState(bundle)
        outState.putBundle("scores", bundle)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        customScoresScreen.restoreState(savedInstanceState)
    }

    private fun setDisplay() {
        customScoresScreen = CustomScoresScreen(this, object : FileChooserInterface {
            override fun onFileCallback(parameters: ValueCallback<Array<Uri?>>) {
                chooseCallback = parameters
            }
        })

        binding.root.addView(customScoresScreen)
        customScoresScreen.initCustomScoresContainer(getContent, binding.root)

        val infoGetter = InfoGetter(this)
        val currentLink = infoGetter.readLink()

        Log.d("123123", "Data from infoGetter is $currentLink")

        customScoresScreen.loadUrl(currentLink)

        setWebClicks(customScoresScreen)
    }

    private fun setWebClicks(webview : WebView){
        onBackPressedDispatcher.addCallback(this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (webview.canGoBack()) {
                        webview.goBack()
                    }
                }
            })
    }
}