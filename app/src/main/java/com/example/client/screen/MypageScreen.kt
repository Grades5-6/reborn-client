package com.example.client.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
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
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.client.R
import com.example.client.component.all.ButtonColorEnum
import com.example.client.component.all.ButtonComponent
import com.example.client.component.mypage.CertificateItemComponent
import com.example.client.component.mypage.JobFieldViewComponent
import com.example.client.component.mypage.RebornTemperatureComponent
import com.example.client.data.model.viewmodel.SharedCertificationViewModel
import com.example.client.data.model.response.LicenseResponse
import com.example.client.data.model.viewmodel.MyPageViewModel
import com.example.client.domain.TestUserInfo
import java.util.Date

@Composable
fun MyPageScreen(
    myPageViewModel: MyPageViewModel,
    sharedCertificationViewModel: SharedCertificationViewModel,
    navController: NavController
) {
    var nickname by remember { mutableStateOf<String?>(null) }
    val user by myPageViewModel.user.collectAsState()

    LaunchedEffect(Unit) {
        nickname = TestUserInfo.TEST_USERNAME
        myPageViewModel.getUser()
        TestUserInfo.USERIMG = user?.profileImg ?: TestUserInfo.USERIMG
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
                    .fillMaxWidth(),
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

                Icon(
                    painter = painterResource(id = R.drawable.rounded_edit_square_24),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 20.dp, start = 300.dp)
                        .clickable {
                            navController.navigate("MyPageProfile")
                        }
                )

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

                if (!user?.profileImg.isNullOrEmpty()) {
                    // 프로필 이미지가 있는 경우
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(user?.profileImg)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .width(83.dp)
                            .height(83.dp)
                            .clip(CircleShape),
                        error = painterResource(id = R.drawable.icon_rebornlogo),  // 이미지 로드 실패시 기본 이미지
                        placeholder = painterResource(id = R.drawable.icon_rebornlogo)  // 로딩 중 표시할 이미지
                    )
                } else {
                    // 프로필 이미지가 없는 경우 기본 이미지 표시
                    Image(
                        painter = painterResource(id = R.drawable.icon_rebornlogo),
                        contentDescription = "Icon_rebornlogo",
                        modifier = Modifier
                            .width(108.dp)
                            .height(83.dp)
                            .padding(bottom = 5.dp)
                    )
                }
                Spacer(modifier = Modifier.size(10.dp))

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
                RebornTemperatureComponent(
                    temperature = (user?.rebornTemperature ?: 1) * 0.01f,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
                HorizontalDivider(thickness = 1.dp, color = Color(0xFF48582F))

                Icon(
                    painter = painterResource(id = R.drawable.rounded_edit_square_24),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 20.dp, start = 300.dp)
                        .clickable {
                            navController.navigate("MyPageInterest")
                        }
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

                Icon(
                    painter = painterResource(id = R.drawable.rounded_edit_square_24),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 20.dp, start = 300.dp)
                        .clickable {
                            navController.navigate("MyPageRegion")
                        }
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

                user?.let {
                    ButtonComponent(
                        buttonText = it.region,
                        buttonColorType = ButtonColorEnum.Green,
                        onClick = {})
                }
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

