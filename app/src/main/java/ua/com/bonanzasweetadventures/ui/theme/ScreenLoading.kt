package ua.com.bonanzasweetadventures.ui.theme

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import ua.com.bonanzasweetadventures.BonanzaViewModel
import ua.com.bonanzasweetadventures.Constants
import ua.com.bonanzasweetadventures.R
import ua.com.bonanzasweetadventures.SweetActivity
import ua.com.bonanzasweetadventures.data.InfoGetter


@Composable
fun ScreenLoading(navHostController: NavHostController, bonanzaViewModel: BonanzaViewModel) {

    val activity = LocalContext.current as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    val context = LocalContext.current

    val status = bonanzaViewModel.liveStatus.observeAsState()

    Log.d("123123", "The status in ScreenLoading is ${status.value}")

    if (status.value == "Ready"){
        val intent = Intent(context, SweetActivity::class.java)
        context.startActivity(intent)
    }


    val infiniteTransition = rememberInfiniteTransition()
    val angle = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )



    LaunchedEffect(Unit){
        val infoGetter = InfoGetter(context)
        val currentData = infoGetter.readLink()

        Log.d("123123", "ScreenLoading currentData is $currentData")

        when(currentData){
            Constants.CLEAR -> { infoGetter.prepearDestination(bonanzaViewModel) }
            Constants.ATTENTION -> { navHostController.navigate(Screens.Menu.target) }
            else -> {
                if (currentData.length > 10){
                    bonanzaViewModel.putDataToLiveStatus("Ready")
                }
            }
        }
    }


    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = "loading bg",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.Center)
        ) {

            Image(
                painter = painterResource(id = R.drawable.ele3),
                contentDescription = "loading logo",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .graphicsLayer {
                        rotationZ = angle.value
                    }
            )

            Text(
                text = "Loading...",
                color = Color.DarkGray,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}