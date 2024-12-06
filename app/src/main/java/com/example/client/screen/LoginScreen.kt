package com.example.client.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.client.R
import com.example.client.data.model.viewmodel.MyPageViewModel
import com.example.client.domain.TestUserInfo

// [todo]: login view 출력
@Composable
fun LoginScreen(
    myPageViewModel: MyPageViewModel,
    navController: NavController) {
    val context = LocalContext.current

    val user by myPageViewModel.user.collectAsState()

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginStatus by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFBDC))

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 90.dp)
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
                text = "간편 로그인",
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
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 16.dp)
        ) {
            TextField(
                value = username,
                onValueChange = { username = it },
                placeholder = {
                    Text(
                        text = "이름을 입력하세요.",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.jura)),
                            color = Color(0xFF48582F),
                            textAlign = TextAlign.Center,
                        )
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFA5B985),
                    unfocusedContainerColor = Color(0xFFA5B985)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            TextField(
                value = password,
                onValueChange = { password = it },
                placeholder = {
                    Text(
                        text = "비밀번호를 입력하세요",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.jura)),
                            color = Color(0xFF48582F),
                            textAlign = TextAlign.Center,
                        )
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFA5B985),
                    unfocusedContainerColor = Color(0xFFA5B985)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                visualTransformation = PasswordVisualTransformation()
            )

            Button(
                onClick = {
                    if (username == TestUserInfo.TEST_USERNAME && password == TestUserInfo.TEST_PASSWORD) {
                        myPageViewModel.getUser()
                        TestUserInfo.USERIMG = user?.profileImg ?: ""
                        TestUserInfo.REGION = user?.region ?: ""
                        TestUserInfo.INTEREST = user?.interestedField ?: listOf()
                        TestUserInfo.EMPLOYMENT = user?.employmentStatus ?: ""
                        TestUserInfo.REBORNTEMPERATURE = user?.rebornTemperature ?: 0
                        //todo: 자격증 받아오기
                        println(TestUserInfo.USERIMG)

                        if(TestUserInfo.EMPLOYMENT == "" || TestUserInfo.INTEREST.isEmpty() || TestUserInfo.REGION == ""){
                            navController.navigate("MainOnboarding")
                        }else{
                            navController.navigate("Main")
                        }
                    } else {
                        Toast.makeText(context,"로그인 실패",Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF47572F)
                )
            ) {
                Text(
                    text = "로그인"
                )
            }
        }


        //todo : 카카오 로그인 버튼 및 onclick event
        Image(
            painter = painterResource(id = R.drawable.icon_kakaologin),
            contentDescription = "Icon_kakaologin",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(5f)
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp)
                .clickable {  }
            //loginWithKakao(context)
        )
    }
}

