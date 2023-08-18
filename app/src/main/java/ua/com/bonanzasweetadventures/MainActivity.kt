package ua.com.bonanzasweetadventures

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import ua.com.bonanzasweetadventures.ui.theme.BonanzaSweetAdventuresTheme
import ua.com.bonanzasweetadventures.ui.theme.NavGraph

class MainActivity : ComponentActivity() {

    private val bonanzaViewModel by viewModels<BonanzaViewModel>()
    val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        //do some work
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("123123", "onCreate in MainActivity")

        setContent {
            BonanzaSweetAdventuresTheme {
                // A surface container using the 'background' color from the theme
                NavGraph(bonanzaViewModel)
            }
        }

        askPermission()
    }

    private fun askPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val permission = android.Manifest.permission.POST_NOTIFICATIONS
            if (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {
                //do some work
            } else requestPermissionLauncher.launch(permission)
        } else {
            //do some work
        }
    }
}

