package com.example.client.screen

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.example.client.component.job.CertificateButtonComponent
import com.example.client.component.job.JobListComponent
import com.example.client.component.job.KeywordButtonComponent
import com.example.client.component.all.TabLayoutComponent
import com.example.client.data.model.viewmodel.JobPostViewModel
import com.example.client.domain.TestUserInfo
import androidx.compose.ui.platform.LocalContext
import com.example.client.component.all.ButtonColorEnum
import com.example.client.component.mypage.CertificateItemComponent
import com.example.client.data.model.response.LicenseResponse
import com.example.client.data.model.response.LicensesGetResponse
import com.example.client.data.model.viewmodel.JobPostLicenseViewModel
import java.util.Date

@Composable
fun JobMainScreen(
    jobPostViewModel: JobPostViewModel,
    jobPostLicenseViewModel: JobPostLicenseViewModel,
    navController: NavController
) {
    // Context를 상위 레벨에서 가져옴
    val context = LocalContext.current

    var nickname by remember { mutableStateOf<String?>(null) }
    val showSearchBar = remember { mutableStateOf(false) }
    var searchKeyword by remember { mutableStateOf("") }

    // 상태 수집
    val jobList by jobPostViewModel.jobList.collectAsState()
    val isLoading by jobPostViewModel.isLoading.collectAsState()
    val error by jobPostViewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        nickname = TestUserInfo.TEST_USERNAME
        jobPostViewModel.getJob()
    }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFBDC))
    ) {
        item {
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
                Icon(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 230.dp)
                        .clickable {
                            showSearchBar.value = !showSearchBar.value
                            Log.e("showSearchBar", "${showSearchBar.value}")
                        }
                        .padding(8.dp),
                    tint = Color(0xFF48582F)
                )
            }
        }

        if (showSearchBar.value) {
            item {
                Row(
                    modifier = Modifier
                        .background(color = ButtonColorEnum.Gray.color.copy(alpha = 0.3f))
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextField(
                        value = searchKeyword,
                        onValueChange = { searchKeyword = it },
                        modifier = Modifier.weight(1f),
                        label = { Text("가장 하고 싶은 일이 무엇인가요?") },
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                        )
                    )
                    Icon(
                        painter = painterResource(R.drawable.ic_send),
                        contentDescription = null,
                        tint = ButtonColorEnum.Green.color,
                        modifier = Modifier
                            .clickable {
                                jobPostViewModel.searchJob(searchKeyword)
                            }
                            .padding(start = 8.dp)
                            .align(Alignment.CenterVertically),
                    )
                }
            }
        }

        item {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xFFFFFEF4), shape = RoundedCornerShape(size = 40.dp))
            ) {
                Row() {
                    Column(modifier = Modifier.padding(top = 20.dp, start = 20.dp)) {
                        Row {
                            Text(
                                text = "$nickname",
                                fontSize = 30.sp,
                                color = Color(0xFF000000),
                                modifier = Modifier.height(35.dp),
                                fontFamily = FontFamily(Font(R.font.pretendardextrabold))
                            )
                            Text(
                                text = "님께",
                                fontSize = 30.sp,
                                color = Color(0xFF000000),
                                modifier = Modifier.height(35.dp),
                                fontFamily = FontFamily(Font(R.font.pretendardregular))
                            )
                            Spacer(modifier = Modifier.width(120.dp))
                            TextButton(
                                onClick = { navController.navigate("MyPage") },
                                enabled = true,
                            ) {
                                Text(
                                    text = "내 정보 편집",
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        lineHeight = 21.sp,
                                        fontFamily = FontFamily(Font(R.font.pretendardregular)),
                                        fontWeight = FontWeight(400),
                                        color = Color(0xFF688142),
                                        textAlign = TextAlign.Center,
                                    )
                                )
                            }
                        }
                        Text(
                            text = "추천드리는 공고",
                            fontSize = 30.sp,
                            color = Color(0xFF000000),
                            modifier = Modifier.height(35.dp),
                            fontFamily = FontFamily(Font(R.font.pretendardregular))
                        )
                    }
                    Spacer(modifier = Modifier.size(width = 185.dp, height = 20.dp))
                }
                Spacer(modifier = Modifier.size(20.dp))
                TabLayoutComponent(tabs = listOf("맞춤 일자리", "자격증 기반")) { page ->
                    when (page) {
                        0 -> {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(min = 800.dp)
                                    .padding(top = 20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                LazyRow(
                                    modifier = Modifier.padding(horizontal = 10.dp),
                                    userScrollEnabled = true,
                                    contentPadding = PaddingValues(end = 10.dp)
                                ) {
                                    item {
                                        // 지역 버튼 추가
                                        KeywordButtonComponent(
                                            buttonText = TestUserInfo.REGION,
                                            onClick = {}
                                        )
                                        Spacer(modifier = Modifier.size(10.dp))
                                        KeywordButtonComponent(
                                            buttonText = "${TestUserInfo.YEAR} / ${TestUserInfo.SEX}",
                                            onClick = {}
                                        )
                                    }
                                    items(TestUserInfo.INTEREST) { interest ->
                                        Spacer(modifier = Modifier.size(10.dp))
                                        KeywordButtonComponent(
                                            buttonText = interest,
                                            onClick = {}
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.size(15.dp))

                                when {
                                    isLoading -> {
                                        CircularProgressIndicator(
                                            modifier = Modifier.padding(16.dp),
                                            color = Color(0xFF48582F)
                                        )
                                    }

                                    error != null -> {
                                        Text(
                                            text = "데이터를 불러오는데 실패했습니다",
                                            color = Color.Red,
                                            modifier = Modifier.padding(16.dp)
                                        )
                                    }

                                    jobList.isEmpty() -> {
                                        Text(
                                            text = "표시할 일자리가 없습니다",
                                            modifier = Modifier.padding(16.dp)
                                        )
                                    }

                                    else -> {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth(),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            jobList.forEach { job ->
                                                JobListComponent(
                                                    companyName = job.companyName,
                                                    jobTitle = job.jobTitle,
                                                    location = job.workAddr,
                                                    onClick = {
                                                        if (job.hmUrl != null) {
                                                            // URL이 있는 경우 브라우저 실행
                                                            if (!job.hmUrl.startsWith("https://")) {
                                                                job.hmUrl = "https://${job.hmUrl}"
                                                            }
                                                            val intent = Intent(
                                                                Intent.ACTION_VIEW,
                                                                Uri.parse(job.hmUrl)
                                                            )
                                                            context.startActivity(intent)
                                                        } else {
                                                            // URL이 없는 경우 알림창 표시
                                                            Toast.makeText(
                                                                context,
                                                                "일자리 상세 페이지가 존재하지 않습니다.",
                                                                Toast.LENGTH_SHORT
                                                            ).show()
                                                        }
                                                    }
                                                )
                                                Spacer(modifier = Modifier.height(8.dp))
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        1 -> {
                            var isLicenseView by remember { mutableStateOf(true) }
                            val isLoading by jobPostLicenseViewModel.isLoading.collectAsState()
                            val error by jobPostLicenseViewModel.error.collectAsState()
                            val jobs by jobPostLicenseViewModel.jobList.collectAsState()

                            // 디버깅을 위한 로그 추가
                            LaunchedEffect(jobs) {
                                Log.d("JobMainScreen", "Jobs updated: ${jobs.size}")
                            }

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .heightIn(min = 800.dp)
                                    .padding(top = 25.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                if (isLicenseView) {
                                    Text(
                                        text = "확인하고 싶은 자격증을 선택해주세요!",
                                        style = TextStyle(
                                            fontSize = 20.sp,
                                            lineHeight = 40.sp,
                                            fontFamily = FontFamily(Font(R.font.pretendardextrabold)),
                                            fontWeight = FontWeight(600),
                                            color = Color(0xFF000000),
                                            textAlign = TextAlign.Center,
                                        )
                                    )
                                    Spacer(modifier = Modifier.size(15.dp))
                                    LazyVerticalGrid(
                                        columns = GridCells.Fixed(2),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(1000.dp)
                                            .padding(horizontal = 8.dp),
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        verticalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        items(TestUserInfo.LICENSES) { license ->
                                            CertificateButtonComponent(
                                                license = LicensesGetResponse(
                                                    jmfldnm = license.jmfldnm,
                                                    seriesnm = license.seriesnm,
                                                    expirationDate = license.expirationDate
                                                ),
                                                onClick = {
                                                    jobPostLicenseViewModel.getJobLicense(license.jmfldnm)
                                                    isLicenseView = false
                                                }
                                            )
                                        }
                                    }
                                } else {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        TextButton(
                                            onClick = { isLicenseView = true },
                                            modifier = Modifier.padding(bottom = 8.dp)
                                        ) {
                                            Text(
                                                text = "자격증 목록으로 돌아가기",
                                                style = TextStyle(
                                                    fontSize = 20.sp,
                                                    fontFamily = FontFamily(Font(R.font.pretendardregular)),
                                                    fontWeight = FontWeight(600),
                                                    color = Color(0xFF688142),
                                                )
                                            )
                                        }

                                        when {
                                            isLoading -> {
                                                CircularProgressIndicator(
                                                    modifier = Modifier.align(Alignment.CenterHorizontally),
                                                    color = Color(0xFF48582F)
                                                )
                                            }
                                            error != null -> {
                                                Text(
                                                    text = error ?: "오류가 발생했습니다",
                                                    modifier = Modifier
                                                        .align(Alignment.CenterHorizontally)
                                                        .padding(16.dp),
                                                    color = Color.Red
                                                )
                                            }
                                            jobs.isEmpty() -> {
                                                Text(
                                                    text = "해당 자격증과 관련된 일자리가 없습니다",
                                                    modifier = Modifier
                                                        .align(Alignment.CenterHorizontally)
                                                        .padding(16.dp),
                                                    style = TextStyle(
                                                        fontSize = 16.sp,
                                                        fontFamily = FontFamily(Font(R.font.pretendardregular))
                                                    )
                                                )
                                            }
                                            else -> {
                                                jobs.forEach { job ->
                                                    // 디버깅을 위한 로그 추가
                                                    Log.d("JobMainScreen", "Rendering job: ${job.companyName}")
                                                    JobListComponent(
                                                        companyName = job.companyName ?: "회사명 없음",
                                                        jobTitle = job.jobTitle ?: "제목 없음",
                                                        location = job.workAddr ?: "주소 없음",
                                                        onClick = {
                                                            if (job.hmUrl != null) {
                                                                if (!job.hmUrl.startsWith("https://")) {
                                                                    job.hmUrl = "https://${job.hmUrl}"
                                                                }
                                                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(job.hmUrl))
                                                                context.startActivity(intent)
                                                            } else {
                                                                Toast.makeText(context, "일자리 상세 페이지가 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
                                                            }
                                                        }
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.size(70.dp))
            }
        }

    }
}