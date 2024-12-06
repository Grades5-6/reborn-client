package com.example.client.screen.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.client.R
import com.example.client.component.all.ButtonColorEnum
import com.example.client.component.all.ButtonComponent
import com.example.client.component.onboarding.PickerComponent
import com.example.client.data.model.viewmodel.mypage.EditRegionViewModel
import com.example.client.domain.TestUserInfo

@Composable
fun EditRegionScreen(
    editRegionViewModel: EditRegionViewModel,
    navController: NavController
) {
    var nickname by remember { mutableStateOf<String?>(null) }
    var selectedAnswer by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        nickname = TestUserInfo.TEST_USERNAME
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFEF4))
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = Color(0xFFFFFBDC)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.padding(top = 30.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "나의 동네 수정",
                        style = TextStyle(
                            fontSize = 24.sp,
                            lineHeight = 33.6.sp,
                            fontFamily = FontFamily(Font(R.font.pretendardextrabold)),
                            color = Color(0xFF000000),
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "현재 거주하는 지역",
                    style = TextStyle(
                        fontSize = 26.sp,
                        fontFamily = FontFamily(Font(R.font.pretendardregular)),
                        fontWeight = FontWeight(200),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center,
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
                PickerComponent { submenu ->
                    if (submenu != null) {
                        selectedAnswer = submenu // 선택된 서브 메뉴 저장
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                ButtonComponent(
                    buttonText = "완료",
                    buttonColorType = if (selectedAnswer != "") ButtonColorEnum.Green else ButtonColorEnum.Gray,
                    onClick = {
                        editRegionViewModel.setUserRegion(selectedAnswer)
                        navController.navigate("MyPage")
                    }
                )
            }
        }
    }
}