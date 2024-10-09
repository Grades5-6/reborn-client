package com.example.client

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContent{
            SplashScreen()
        }
    }

    @Preview
    @Composable
    fun SplashScreen(){
        val deepGreen = colorResource(id=R.color.rb_deep_green)
        val lightGreen = colorResource(id=R.color.rb_light_green)
        val beige = colorResource(id=R.color.rb_beige)

        var isLight by remember { mutableStateOf(false) }
        val backgroundColor by animateColorAsState(
            targetValue = if(isLight) lightGreen else deepGreen,
            animationSpec = tween(durationMillis = 1000)
        )


        Surface(modifier=Modifier.fillMaxSize(), color = backgroundColor){
            Column (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(178.dp)
                        .background(beige, shape = androidx.compose.foundation.shape.CircleShape),
                    contentAlignment = Alignment.Center
                ){
                    Image(
                        painter = painterResource(id=R.drawable.icon_rebornlogo),
                        contentDescription = null,
                        modifier = Modifier.size(139.dp)
                    )
                }
                Image(
                    painter = painterResource(id=R.drawable.ic_tv_reborn),
                    contentDescription = null,
                    modifier = Modifier.size(335.dp).padding(0.dp)
                )
                Text(
                    text="중장년층의 새로운 발걸음",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = beige,
                    modifier = Modifier.padding(0.dp)
                )
            }
        }

        LaunchedEffect(Unit) {
            delay(1000)
            isLight=true
            delay(1500)
            Intent(this@SplashActivity, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }.let { startActivity(it) }
        }
    }
}