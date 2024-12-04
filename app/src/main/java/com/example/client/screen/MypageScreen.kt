package com.example.client.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
import com.example.client.component.mypage.CertificateItemComponent
import com.example.client.component.mypage.JobFieldViewComponent
import com.example.client.component.mypage.RebornTemperatureComponent
import com.example.client.data.model.response.LicenseResponse
import com.example.client.data.model.viewmodel.MyPageViewModel
import com.example.client.domain.TestUserInfo
import java.util.Date

@Composable
fun MyPageScreen(
    myPageViewModel: MyPageViewModel,
    navController: NavController
) {
    var nickname by remember { mutableStateOf<String?>(null) }

    val user by myPageViewModel.user.collectAsState()
    val isLoading by myPageViewModel.isLoading.collectAsState()
    val error by myPageViewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        nickname = TestUserInfo.TEST_USERNAME
        myPageViewModel.getUser()
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
                    .background(color = Color(0xFFFFFBDC))
            ) {
                Row(
                    modifier = Modifier.padding(start = 15.dp, top = 50.dp)
                ) {
                    Text(
                        text = "내 정보",
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
                    .padding(vertical = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.rounded_edit_square_24),
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                    Text("를 누르면 편집할 수 있습니다")
                }

                HorizontalDivider(thickness = 1.dp, color = Color(0xFF48582F))

                // todo : 사용자 닉네임 수정
                Icon(
                    painter = painterResource(id = R.drawable.rounded_edit_square_24),
                    contentDescription = null,
                    modifier = Modifier.padding(top = 20.dp, start = 300.dp)
                )

                // todo : 사용자 현 재직 상태 표시
                user?.let {
                    Text(
                        text = it.employmentStatus,
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 33.6.sp,
                            fontFamily = FontFamily(Font(R.font.pretendardregular)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                            textAlign = TextAlign.Center,
                        ),
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                }

                Image(
                    painter = painterResource(id = R.drawable.icon_rebornlogo),
                    contentDescription = "Icon_rebornlogo",
                    modifier = Modifier
                        .width(108.dp)
                        .height(83.dp)
                        .padding(bottom = 5.dp)
                )

                nickname?.let {
                    Text(
                        text = it,
                        style = TextStyle(
                            fontSize = 24.sp,
                            lineHeight = 33.6.sp,
                            fontFamily = FontFamily(Font(R.font.pretendardextrabold)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF757575),
                            textAlign = TextAlign.Center,
                        ),
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                }
                RebornTemperatureComponent(temperature = (user?.rebornTemperature ?: 1) * 0.01f, modifier = Modifier.padding(bottom = 20.dp))
                HorizontalDivider(thickness = 1.dp, color = Color(0xFF48582F))

                //todo: 관심 분야 수정 아이콘
                Icon(
                    painter = painterResource(id = R.drawable.rounded_edit_square_24),
                    contentDescription = null,
                    modifier = Modifier.padding(top = 20.dp, start = 300.dp)
                )

                Text(
                    text = "관심 분야",
                    style = TextStyle(
                        fontSize = 24.sp,
                        lineHeight = 33.6.sp,
                        fontFamily = FontFamily(Font(R.font.pretendardextrabold)),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center,
                    )
                )

                JobFieldViewComponent(
                    selectedFields = user?.interestedField ?: emptyList()
                )
                HorizontalDivider(thickness = 1.dp, color = Color(0xFF48582F))

                // todo: 나의 동네 수정 아이콘
                Icon(
                    painter = painterResource(id = R.drawable.rounded_edit_square_24),
                    contentDescription = null,
                    modifier = Modifier.padding(top = 20.dp, start = 300.dp)
                )

                Text(
                    text = "나의 동네",
                    style = TextStyle(
                        fontSize = 24.sp,
                        lineHeight = 33.6.sp,
                        fontFamily = FontFamily(Font(R.font.pretendardextrabold)),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center,
                    ),
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                user?.let { ButtonComponent(buttonText = it.region, buttonColorType = ButtonColorEnum.Green) { } }
                Spacer(modifier = Modifier.size(30.dp))
                HorizontalDivider(thickness = 1.dp, color = Color(0xFF48582F))

                // todo: 나의 자격증 수정 아이콘
                Icon(
                    painter = painterResource(id = R.drawable.rounded_edit_square_24),
                    contentDescription = null,
                    modifier = Modifier.padding(top = 20.dp, start = 300.dp)
                )
                Text(
                    text = "나의 자격증",
                    style = TextStyle(
                        fontSize = 24.sp,
                        lineHeight = 33.6.sp,
                        fontFamily = FontFamily(Font(R.font.pretendardextrabold)),
                        color = Color(0xFF000000),
                        textAlign = TextAlign.Center,
                    ),
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                Spacer(modifier = Modifier.size(15.dp))
                
                user?.licenses?.forEach { license ->
                    CertificateItemComponent(
                        license = LicenseResponse(
                            jmfldnm = license.jmfldnm,
                            seriesnm = license.seriesnm,
                            //todo: 자격증 Date 추가
                            date = Date()
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                Spacer(modifier = Modifier.size(30.dp))
            }
        }
    }
}

