package com.example.client.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.client.R
import com.example.client.component.all.ButtonColorEnum
import com.example.client.component.all.ButtonComponent
import com.example.client.component.all.JobField
import com.example.client.component.all.JobFieldComponent
import com.example.client.component.onboarding.PageIndexComponent
import com.example.client.component.onboarding.PickerComponent
import com.example.client.domain.TestUserInfo
import com.example.client.data.model.viewmodel.MainOnBoardingViewModel

@Composable
fun MainOnboardingScreen(mainOnBoardingViewModel: MainOnBoardingViewModel = viewModel(), navController: NavController) {

    val nickname = TestUserInfo.TEST_USERNAME

    val questions = listOf(
        "현재 경제활동 상태",
        "현재 거주하는 지역",
        "관심 있는 분야"
    )

    var currentQuestionIndex by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf("") }
    val additionalAnswers = remember { mutableStateListOf<String>() }
    var selectedJobFields by remember { mutableStateOf<List<JobField>>(emptyList()) }

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
            Row() {
                Column(modifier = Modifier.padding(top = 20.dp, start = 20.dp)) {
                    Text(
                        text = "반가워요,\n",
                        fontSize = 30.sp,
                        color = Color(0xFF000000),
                        modifier = Modifier.height(35.dp),
                        fontFamily = FontFamily(Font(R.font.pretendardregular))
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = "$nickname 님!",
                        fontSize = 30.sp,
                        color = Color(0xFF000000),
                        modifier = Modifier.height(35.dp),
                        fontFamily = FontFamily(Font(R.font.pretendardextrabold))
                    )
                }
                Spacer(modifier = Modifier.size(width = 185.dp, height = 20.dp))
                PageIndexComponent(currentPage = currentQuestionIndex + 1, totalPage = questions.size)
            }
            Spacer(modifier = Modifier.size(70.dp))
            Text(
                text = questions[currentQuestionIndex],
                fontSize = 30.sp,
                modifier = Modifier
                    .height(40.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                fontFamily = FontFamily(Font(R.font.pretendardregular))
            )
            Spacer(modifier = Modifier.size(70.dp))

            when (currentQuestionIndex) {
                0 -> {
                    val jobFields = listOf("재직", "퇴직")
                    jobFields.forEach { field ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedAnswer = field
                                }
                                .padding(8.dp),
                        ) {
                            Text(
                                text = field,
                                fontSize = if (selectedAnswer == field) 40.sp else 32.sp,
                                modifier = Modifier
                                    .height(40.dp)
                                    .align(alignment = Alignment.CenterHorizontally),
                                fontFamily = FontFamily(Font(R.font.pretendardextrabold)),
                                color = if (selectedAnswer == field) Color(0xFF48582F) else Color(0xff979797)
                            )
                            Spacer(modifier = Modifier.size(20.dp))
                        }
                    }
                }

                1 -> {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                            .align(alignment = Alignment.CenterHorizontally),
                        contentAlignment = Alignment.Center,
                    ) {
                        PickerComponent { submenu ->
                            if (submenu != null) {
                                selectedAnswer = submenu // 선택된 서브 메뉴 저장
                            }
                        }
                    }
                }

                2 -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(alignment = Alignment.CenterHorizontally),
                    ) {
                        JobFieldComponent { jobFields ->
                            selectedJobFields = jobFields // 선택된 직업 필드를 저장
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(70.dp))

            val text = if (currentQuestionIndex < questions.size - 1) "다음 질문" else "제출"
            Column(
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            ) {
                ButtonComponent(
                    buttonText = text,
                    buttonColorType = if (currentQuestionIndex < questions.size - 1) ButtonColorEnum.Green else ButtonColorEnum.LightGreen,
                    onClick = {
                        if (currentQuestionIndex < questions.size - 1) {
                            additionalAnswers.add(selectedAnswer) // 현재 선택된 답변 추가
                            selectedAnswer = "" // 선택 초기화
                            currentQuestionIndex++ // 다음 질문으로 이동
                        } else {
                            additionalAnswers.add(selectedAnswer) // 마지막 질문의 답변 추가
                            // API 요청 구현
                            mainOnBoardingViewModel.submitOnboarding(
                                additionalAnswers[0],
                                additionalAnswers[1],
                                selectedJobFields.map { it.name } // 직업 필드를 배열로 전달
                            )
                            TestUserInfo.REGION = additionalAnswers[1]
                            TestUserInfo.INTEREST = selectedJobFields.map { it.name }
                            TestUserInfo.EMPLOYMENT = additionalAnswers[0]

                            navController.navigate("Main")
                        }
                    }
                )
            }
        }
    }
}

