package ua.com.bonanzasweetadventures.ui.theme

import android.app.Activity
import android.content.pm.ActivityInfo
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import ua.com.bonanzasweetadventures.R


@Composable
fun ScreenBonanza(navHostController: NavHostController){

    val activity = LocalContext.current as Activity
    activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    Box(modifier = Modifier
        .fillMaxSize()
    ){
        GameDecor(navHostController)
        GamePlayground()
    }
}

@Composable
fun BoxScope.GamePlayground(){

    val screenHeight = LocalConfiguration.current.screenHeightDp
    val score = remember{
        mutableStateOf(0)
    }

    val isVis1 = remember {
        mutableStateOf(true)
    }

    val isVis2 = remember {
        mutableStateOf(true)
    }

    val isVis3 = remember {
        mutableStateOf(true)
    }

    val isVis4 = remember {
        mutableStateOf(true)
    }

    val isVis5 = remember {
        mutableStateOf(true)
    }

    val isVis6 = remember {
        mutableStateOf(true)
    }



    val ani1 = remember {
        Animatable(initialValue = 0f)
    }

    val ani2 = remember {
        Animatable(initialValue = 0f)
    }

    val ani3 = remember {
        Animatable(initialValue = 0f)
    }

    val ani4 = remember {
        Animatable(initialValue = 0f)
    }

    val ani5 = remember {
        Animatable(initialValue = 0f)
    }

    val ani6 = remember {
        Animatable(initialValue = 0f)
    }

    LaunchedEffect(Unit){
        ani1.animateTo(
            targetValue = screenHeight.toFloat(),
            animationSpec = infiniteRepeatable(
                tween(3000, delayMillis = 150, easing = FastOutLinearInEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    LaunchedEffect(Unit){
        ani2.animateTo(
            targetValue = screenHeight.toFloat(),
            animationSpec = infiniteRepeatable(
                tween(2500, delayMillis = 300, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    LaunchedEffect(Unit){
        ani3.animateTo(
            targetValue = screenHeight.toFloat(),
            animationSpec = infiniteRepeatable(
                tween(2300, delayMillis = 400, easing = FastOutLinearInEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    LaunchedEffect(Unit){
        ani4.animateTo(
            targetValue = screenHeight.toFloat(),
            animationSpec = infiniteRepeatable(
                tween(2900, delayMillis = 200, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    LaunchedEffect(Unit){
        ani5.animateTo(
            targetValue = screenHeight.toFloat(),
            animationSpec = infiniteRepeatable(
                tween(2750, delayMillis = 50, easing = FastOutLinearInEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    LaunchedEffect(Unit){
        ani6.animateTo(
            targetValue = screenHeight.toFloat(),
            animationSpec = infiniteRepeatable(
                tween(2000, delayMillis = 100, easing = FastOutLinearInEasing),
                repeatMode = RepeatMode.Restart
            )
        )
    }

    LaunchedEffect(Unit){
        repeat(12){
            delay(5000)
            isVis1.value = true
            isVis2.value = true
            isVis3.value = true
            isVis4.value = true
            isVis5.value = true
            isVis6.value = true
        }
    }


    if (isVis1.value){
        Image(
            painter = painterResource(id = R.drawable.ele11),
            contentDescription = "element",
            modifier = Modifier
                .size(64.dp)
                .offset(y = ani1.value.dp, x = 128.dp)
                .clickable {
                    score.value += 1
                    isVis1.value = false
                }
        )
    }


    if (isVis2.value){
        Image(
            painter = painterResource(id = R.drawable.ele10),
            contentDescription = "element",
            modifier = Modifier
                .size(64.dp)
                .offset(y = ani2.value.dp, x = 164.dp)
                .clickable {
                    score.value += 1
                    isVis2.value = false

                }
        )
    }


    if (isVis3.value){
        Image(
            painter = painterResource(id = R.drawable.ele9),
            contentDescription = "element",
            modifier = Modifier
                .size(64.dp)
                .offset(y = ani3.value.dp, x = 184.dp)
                .clickable {
                    score.value += 1
                    isVis3.value = false

                }
        )

    }


    if (isVis4.value){
        Image(
            painter = painterResource(id = R.drawable.ele8),
            contentDescription = "element",
            modifier = Modifier
                .size(64.dp)
                .offset(y = ani4.value.dp, x = 200.dp)
                .clickable {
                    score.value += 1
                    isVis4.value = false
                }
        )
    }


    if (isVis5.value){
        Image(
            painter = painterResource(id = R.drawable.ele7),
            contentDescription = "element",
            modifier = Modifier
                .size(64.dp)
                .offset(y = ani5.value.dp, x = 220.dp)
                .clickable {
                    score.value += 1
                    isVis5.value = false
                }
        )

    }


    if (isVis6.value){
        Image(
            painter = painterResource(id = R.drawable.ele6),
            contentDescription = "element",
            modifier = Modifier
                .size(64.dp)
                .offset(y = ani6.value.dp, x = 100.dp)
                .clickable {
                    score.value += 1
                    isVis6.value = false
                }
        )
    }

    Box(modifier = Modifier.align(Alignment.TopCenter)){
        Image(
            painter = painterResource(id = R.drawable.top_panel),
            contentDescription = "top panel",
            modifier = Modifier.align(Alignment.TopCenter)
        )

        Text(
            text = "Your score is ${score.value}",
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }

    Image(
        painter = painterResource(id = R.drawable.candy_machine),
        contentDescription = "candy machine",
        modifier = Modifier
            .align(Alignment.BottomCenter)
    )
}


@Composable
fun BoxScope.GameDecor(navHostController: NavHostController){
    Image(
        painter = painterResource(id = R.drawable.bg),
        contentDescription = "game back",
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )

    Image(
        painter = painterResource(id = R.drawable.vector),
        contentDescription = "vector",
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .size(640.dp)
            .padding(bottom = 100.dp)
    )

    Image(
        painter = painterResource(id = R.drawable.baseline_close_24),
        contentDescription = "close",
        modifier = Modifier
            .size(64.dp)
            .align(Alignment.BottomStart)
            .clickable {
            navHostController.navigate(Screens.Menu.target)
        }
    )
}

@Composable
@Preview
fun GamePrev(){
    val nav = rememberNavController()
    ScreenBonanza(navHostController = nav)
}