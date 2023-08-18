package ua.com.bonanzasweetadventures

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ua.com.bonanzasweetadventures.data.InfoGetter
import ua.com.bonanzasweetadventures.data.LinkStorage
import ua.com.bonanzasweetadventures.ui.theme.BonanzaSweetAdventuresTheme
import ua.com.bonanzasweetadventures.ui.theme.NavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BonanzaSweetAdventuresTheme {
                // A surface container using the 'background' color from the theme
                NavGraph()
            }
        }

        val linkStorage = LinkStorage(this)
        val currentStatus = linkStorage.getLink()
        if (currentStatus !)

        checkPlayer()
    }


    private fun checkPlayer() : Boolean {
        val infoGetter = InfoGetter(this)
        lifecycleScope.launch {
            infoGetter.prepearDestination()
        }
        return true
    }

    private fun navigateToTheNextScreen(){
        //val intent = Intent(this, )
    }
}

