package com.example.client.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.client.R
import com.example.client.component.all.ButtonColorEnum
import com.example.client.component.all.ButtonComponent
import com.example.client.component.all.CertificateComponent
import com.example.client.component.all.DropDownMenuComponent
import com.example.client.component.onboarding.PageIndexComponent
import com.example.client.data.model.viewmodel.JobOnBoardingViewModel
import com.example.client.data.model.viewmodel.SharedCertificationViewModel
import com.example.client.domain.TestUserInfo
import com.example.client.navigation.NavRoutes

@Composable
fun JobOnBoardingScreen(
    sharedViewModel: SharedCertificationViewModel,
    viewModel: JobOnBoardingViewModel,
    navController: NavController
) {
    val nickname = TestUserInfo.TEST_USERNAME
    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var selectGender by remember { mutableStateOf<String?>(null) }
    var selectBirth by remember { mutableStateOf<String?>(null) }
    val currentYear = 2024
    var ageText by remember { mutableStateOf("") }
    var selectedCertificateAnswer by remember { mutableStateOf<String?>(null) }
    val selectedLicenses by viewModel.selectedLicenses.collectAsState()
    val selectedLicensesCount = selectedLicenses.size

    val questions = listOf(
        "성별",
        "태어난 연도",
        "보유한 자격증"
    )
    val certificateField = listOf("추가하기", "보유한 자격증 없음")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFBDC))
    ) {
        Row(
            modifier = Modifier.padding(start = 15.dp, top = 40.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_rebornlogo),
                contentDescription = "Icon_rebornlogo",
                modifier = Modifier
                    .width(80.dp)
                    .height(66.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color(0xFFFFFEF4), shape = RoundedCornerShape(size = 40.dp))
        ) {
            when (currentQuestionIndex) {
                0 -> {
                    Row {
                        Column(modifier = Modifier.padding(top = 20.dp, start = 20.dp)) {
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(
                                        SpanStyle(
                                            fontSize = 30.sp,
                                            color = Color(0xFF000000),
                                            fontFamily = FontFamily(Font(R.font.pretendardextrabold))
                                        )
                                    ) {
                                        append(nickname)
                                    }
                                    withStyle(
                                        SpanStyle(
                                            fontSize = 30.sp,
                                            color = Color(0xFF000000),
                                            fontFamily = FontFamily(Font(R.font.pretendardregular))
                                        )
                                    ) {
                                        append("님께")
                                    }
                                }
                            )
                            Text(
                                text = "일자리 공고를\n추천드리기 위해\n필요한 정보를 확인할게요",
                                fontSize = 30.sp,
                                color = Color(0xFF000000),
                                fontFamily = FontFamily(Font(R.font.pretendardregular))
                            )
                        }
                        PageIndexComponent(
                            currentPage = currentQuestionIndex + 1,
                            totalPage = questions.size
                        )
                    }
                    Spacer(Modifier.height(90.dp))
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 50.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clickable {
                                    selectGender = "여성"
                                }
                        ) {
                            val imageSize =
                                if (selectGender == "여성") 100.dp else if (selectGender == null) 100.dp else 90.dp
                            val textSize =
                                if (selectGender == "여성") 17.sp else if (selectGender == null) 17.sp else 14.sp
                            Image(
                                painter = painterResource(R.drawable.ic_woman),
                                contentDescription = null,
                                modifier = Modifier.size(imageSize)
                            )
                            Text(
                                text = "여성",
                                textAlign = TextAlign.Center,
                                fontSize = textSize,
                                fontFamily = FontFamily(Font(R.font.pretendardextrabold)),
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                        Spacer(Modifier.width(80.dp))
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .clickable {
                                    selectGender = "남성"
                                }
                        ) {
                            val imageSize =
                                if (selectGender == "남성") 100.dp else if (selectGender == null) 100.dp else 90.dp
                            val textSize =
                                if (selectGender == "남성") 17.sp else if (selectGender == null) 17.sp else 14.sp

                            Image(
                                painter = painterResource(R.drawable.ic_man),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(imageSize)
                            )
                            Text(
                                text = "남성",
                                textAlign = TextAlign.Center,
                                fontSize = textSize,
                                fontFamily = FontFamily(Font(R.font.pretendardextrabold)),
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                    Spacer(Modifier.height(50.dp))
                    ButtonComponent(
                        buttonText = if (selectGender == null) "성별을 입력해주세요" else "다음 질문",
                        buttonColorType = if (selectGender == null) ButtonColorEnum.LightGreen else ButtonColorEnum.Green,
                        onClick = {
                            if (selectGender != null) {
                                currentQuestionIndex++
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                }

                1 -> {
                    Row {
                        Column(modifier = Modifier.padding(top = 20.dp, start = 20.dp)) {
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(
                                        SpanStyle(
                                            fontSize = 30.sp,
                                            color = Color(0xFF000000),
                                            fontFamily = FontFamily(Font(R.font.pretendardextrabold))
                                        )
                                    ) {
                                        append(nickname)
                                    }
                                    withStyle(
                                        SpanStyle(
                                            fontSize = 30.sp,
                                            color = Color(0xFF000000),
                                            fontFamily = FontFamily(Font(R.font.pretendardregular))
                                        )
                                    ) {
                                        append("님께")
                                    }
                                }
                            )
                            Text(
                                text = "일자리 공고를\n추천드리기 위해\n필요한 정보를 확인할게요",
                                fontSize = 30.sp,
                                color = Color(0xFF000000),
                                fontFamily = FontFamily(Font(R.font.pretendardregular))
                            )
                        }
                        PageIndexComponent(
                            currentPage = currentQuestionIndex + 1,
                            totalPage = questions.size
                        )
                    }
                    Spacer(Modifier.height(90.dp))
                    DropDownMenuComponent(
                        list = listOf(
                            "1960",
                            "1961",
                            "1962",
                            "1963",
                            "1964",
                            "1965",
                            "1966",
                            "1967",
                            "1968",
                            "1969"
                        ),
                        onItemSelected = { year ->
                            selectBirth = year
                            ageText = " ${currentYear - year.toInt()}세"
                        },
                        modifier = Modifier
                            .padding(top = 70.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = if (selectBirth != null) ageText else "",
                        fontSize = 32.sp,
                        fontFamily = FontFamily(Font(R.font.pretendardextrabold)),
                        color = Color(0xFF47572F),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 30.dp)
                    )
                    Spacer(Modifier.height(150.dp))
                    ButtonComponent(
                        buttonText = if (selectBirth == null) "태어난 연도를 입력해주세요" else "다음 질문",
                        buttonColorType = if (selectBirth == null) ButtonColorEnum.LightGreen else ButtonColorEnum.Green,
                        onClick = {
                            if (selectBirth != null) {
                                currentQuestionIndex++
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                }

                2 -> {
                    Row {
                        Column(modifier = Modifier.padding(top = 20.dp, start = 20.dp)) {
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(
                                        SpanStyle(
                                            fontSize = 30.sp,
                                            color = Color(0xFF000000),
                                            fontFamily = FontFamily(Font(R.font.pretendardextrabold))
                                        )
                                    ) {
                                        append(nickname)
                                    }
                                    withStyle(
                                        SpanStyle(
                                            fontSize = 30.sp,
                                            color = Color(0xFF000000),
                                            fontFamily = FontFamily(Font(R.font.pretendardregular))
                                        )
                                    ) {
                                        append("님께")
                                    }
                                }
                            )
                            Text(
                                text = "일자리 공고를\n추천드리기 위해\n필요한 정보를 확인할게요",
                                fontSize = 30.sp,
                                color = Color(0xFF000000),
                                fontFamily = FontFamily(Font(R.font.pretendardregular))
                            )
                        }
                        PageIndexComponent(
                            currentPage = currentQuestionIndex + 1,
                            totalPage = questions.size
                        )
                    }
                    Spacer(Modifier.height(90.dp))
                    certificateField.forEach { field ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedCertificateAnswer = field
                                }
                                .padding(8.dp),
                        ) {
                            Text(
                                text = field,
                                fontSize = if (selectedCertificateAnswer == field) 40.sp else 32.sp,
                                modifier = Modifier
                                    .height(40.dp)
                                    .align(alignment = Alignment.CenterHorizontally),
                                fontFamily = FontFamily(Font(R.font.pretendardextrabold)),
                                color = if (selectedCertificateAnswer == field) Color(0xFF48582F) else Color(
                                    0xff979797
                                )
                            )
                        }
                    }
                    Spacer(Modifier.height(100.dp))
                    ButtonComponent(
                        buttonText = if (selectedCertificateAnswer == null) "보유하신 자격증을 추가해주세요" else "다음",
                        buttonColorType = if (selectedCertificateAnswer == null) ButtonColorEnum.LightGreen else ButtonColorEnum.Green,
                        onClick = {
                            if (selectedCertificateAnswer == certificateField[0]) { // 추가하기
                                currentQuestionIndex = 3

                            } else if (selectedCertificateAnswer == certificateField[1]) { // 보유한 자격증 없음
                                navController.navigate(NavRoutes.JobMain.route) {
                                    popUpTo(NavRoutes.JobOnboarding.route) { inclusive = true }
                                }
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                }

                3 -> {
                    val licensesState by sharedViewModel.licensesState.collectAsState()
                    val isLoading by sharedViewModel.isLoading.collectAsState()
                    val errorMessage by sharedViewModel.errorMessage.collectAsState()
                    LaunchedEffect(Unit) {
                        sharedViewModel.fetchLicenses()
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            PageIndexComponent(
                                currentPage = 3,
                                totalPage = questions.size,
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(top = 5.dp, end = 19.dp)
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {

                            when {
                                isLoading -> {
                                    CircularProgressIndicator(
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }

                                errorMessage != null -> {
                                    Text(
                                        text = errorMessage ?: "Unknown Error",
                                        color = Color.Red,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }

                                licensesState.isNotEmpty() -> {
                                    LazyColumn(
                                        modifier = Modifier
                                            .padding(bottom = 100.dp)
                                    ) {
                                        items(licensesState) { license ->
                                            val isSelected by viewModel.isLicenseSelectedFlow(
                                                license
                                            ).collectAsState()
                                            CertificateComponent(
                                                type = license.seriesnm,
                                                name = license.jmfldnm,
                                                date = license.expirationDate ?: "2024-12-05",
                                                isSelected = isSelected,
                                                onItemSelected = { selectedLicense ->
                                                    viewModel.toggleLicencesSelection(
                                                        selectedLicense
                                                    )
                                                },
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .padding(horizontal = 39.dp),
                                            )
                                            Spacer(modifier = Modifier.height(25.dp))
                                        }
                                    }
                                }

                                else -> {
                                    Text(
                                        text = "No certificates available",
                                        modifier = Modifier.align(Alignment.Center),
                                        color = Color.Gray
                                    )
                                }
                            }

                            ButtonComponent(
                                buttonText = if (selectedLicensesCount == 0) "추가하기" else "${selectedLicensesCount}개 추가완료하기",
                                buttonColorType = if (selectedLicensesCount == 0) ButtonColorEnum.LightGreen else ButtonColorEnum.Green,
                                onClick = {
                                    if (selectedLicensesCount != 0) {
                                        currentQuestionIndex++
                                    }
                                },
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                            )
                        }
                    }
                }

                4 -> {
                    Row {
                        Column(modifier = Modifier.padding(top = 20.dp, start = 20.dp)) {
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(
                                        SpanStyle(
                                            fontSize = 30.sp,
                                            color = Color(0xFF000000),
                                            fontFamily = FontFamily(Font(R.font.pretendardextrabold))
                                        )
                                    ) {
                                        append(nickname)
                                    }
                                    withStyle(
                                        SpanStyle(
                                            fontSize = 30.sp,
                                            color = Color(0xFF000000),
                                            fontFamily = FontFamily(Font(R.font.pretendardregular))
                                        )
                                    ) {
                                        append("님께")
                                    }
                                }
                            )
                            Text(
                                text = "일자리 공고를\n추천드리기 위해\n필요한 정보를 확인할게요",
                                fontSize = 30.sp,
                                color = Color(0xFF000000),
                                fontFamily = FontFamily(Font(R.font.pretendardregular))
                            )
                        }
                        PageIndexComponent(
                            currentPage = 3,
                            totalPage = questions.size
                        )
                    }
                    Spacer(Modifier.height(90.dp))
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(selectedLicenses) { license ->
                            Text(
                                text = license.jmfldnm,
                                fontSize = 24.sp,
                                modifier = Modifier
                                    .padding(horizontal = 100.dp, vertical = 8.dp),
                                fontFamily = FontFamily(Font(R.font.pretendardextrabold)),
                                color = Color.Black,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Spacer(Modifier.height(100.dp))
                    ButtonComponent(
                        buttonText = "답변 완료하기",
                        buttonColorType = ButtonColorEnum.Green,
                        onClick = {
                            val sex = selectGender
                            val year = selectBirth?.toInt()
                            if (sex != null && year != null) {
                                viewModel.submitJobOnboarding(sex, year)
                                TestUserInfo.sex=sex
                                TestUserInfo.year=year
                                TestUserInfo.licenses=selectedLicenses.map { it.jmfldnm }
                                navController.navigate(NavRoutes.JobMain.route) {
                                    popUpTo(NavRoutes.JobOnboarding.route) { inclusive = true }
                                }
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }

        }
    }
}


