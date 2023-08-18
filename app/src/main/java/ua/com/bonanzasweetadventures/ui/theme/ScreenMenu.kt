package ua.com.bonanzasweetadventures.ui.theme

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset.Companion.Infinite
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import ua.com.bonanzasweetadventures.R


@Composable
fun ScreenMenu(navHostController: NavHostController){


    val activity = LocalContext.current as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT


    val alphaValue1 = remember { Animatable(1f) }
    val alphaValue2 = remember { Animatable(1f) }
    val alphaValue3 = remember { Animatable(1f) }
    val alphaValue4 = remember { Animatable(1f) }
    val alphaValue5 = remember { Animatable(1f) }

    LaunchedEffect(Unit) {
        delay(100)
        alphaValue1.animateTo(
            targetValue = 0f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 800, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
    }

    LaunchedEffect(Unit) {
        delay(200)
        alphaValue2.animateTo(
            targetValue = 0f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 800, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
    }

    LaunchedEffect(Unit) {
        delay(300)
        alphaValue3.animateTo(
            targetValue = 0f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 800, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
    }

    LaunchedEffect(Unit) {
        delay(400)
        alphaValue4.animateTo(
            targetValue = 0f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 800, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
    }

    LaunchedEffect(Unit) {
        delay(500)
        alphaValue5.animateTo(
            targetValue = 0f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 800, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            )
        )
    }

    Box(modifier = Modifier
        .fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = "menu back",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Box(modifier = Modifier
            .align(Alignment.TopCenter)
        ){
            Image(
                painter = painterResource(id = R.drawable.top_panel),
                contentDescription = "Top panel"
            )

            Text(
                text = "Bonanza Sweet Adventures",
                color = Color.White,
                modifier = Modifier.align(
                    Alignment.Center
                )
            )
        }


        Column(modifier = Modifier
            .align(Alignment.TopCenter)
            .padding(top = 64.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ele1),
                contentDescription = "element",
                modifier = Modifier
                    .size(64.dp)
                    .alpha(alphaValue1.value)
            )

            Image(
                painter = painterResource(id = R.drawable.ele2),
                contentDescription = "element",
                modifier = Modifier
                    .size(64.dp)
                    .alpha(alphaValue2.value)
            )

            Image(
                painter = painterResource(id = R.drawable.ele3),
                contentDescription = "element",
                modifier = Modifier
                    .size(64.dp)
                    .alpha(alphaValue3.value)
            )

            Image(
                painter = painterResource(id = R.drawable.ele4),
                contentDescription = "element",
                modifier = Modifier
                    .size(64.dp)
                    .alpha(alphaValue4.value)
            )

            Image(
                painter = painterResource(id = R.drawable.ele5),
                contentDescription = "element",
                modifier = Modifier
                    .size(64.dp)
                    .alpha(alphaValue5.value)
            )
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)
            .padding(bottom = 64.dp)
        ) {

            Box(modifier = Modifier
                .align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.text),
                    contentDescription = "btn back",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable {
                            navHostController.navigate(Screens.Bonanza.target)
                        }
                )
                Text(
                    text = "Play game",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }


            Box(modifier = Modifier
                .align(Alignment.CenterHorizontally)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.text),
                    contentDescription = "btn back",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable {
                            navHostController.navigate(Screens.Help.target)
                        }
                )
                Text(
                    text = "Help Screen",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}


@Composable
@Preview
fun MenuPreview(){
    val nav = rememberNavController()
    ScreenMenu(navHostController = nav)
}