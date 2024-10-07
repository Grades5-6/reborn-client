package com.example.client.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.client.R
import com.example.client.domain.loginWithKakao

// [todo]: login view 출력
@Composable
fun LoginScreen() {
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
            .background(color = Color(0xFFFFFBDC))
    ) {
        Image(
            painter = painterResource(id = R.drawable.icon_rebornlogo),
            contentDescription = "Icon_rebornlogo",
            modifier = Modifier
                .width(108.dp)
                .height(83.dp)
                .padding(bottom = 15.dp)
        )
        Text(
            text = "간편 회원가입",
            style = TextStyle(
                fontSize = 30.sp,
                lineHeight = 45.sp,
                fontFamily = FontFamily(Font(R.font.jura)),
                fontWeight = FontWeight(700),
                color = Color(0xFF48582F),
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier.padding(bottom = 15.dp),
        )
        Text(
            text = "카카오 아이디로 간편하게\n‘리본' 서비스에 가입하실 수 있습니다.",
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 30.sp,
                fontFamily = FontFamily(Font(R.font.jura)),
                fontWeight = FontWeight(300),
                color = Color(0xFF000000),
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier.padding(bottom = 100.dp),
        )

        //todo : 카카오 로그인 버튼 및 onclick event
        Image(
            painter = painterResource(id = R.drawable.icon_kakaologin),
            contentDescription = "Icon_kakaologin",
            modifier = Modifier
                .width(322.dp)
                .height(60.dp)
                .clickable { loginWithKakao(context)  }
        )
    }
}

