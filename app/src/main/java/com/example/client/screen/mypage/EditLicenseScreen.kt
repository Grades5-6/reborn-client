package com.example.client.screen.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
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
import com.example.client.component.all.CertificateComponent
import com.example.client.data.model.viewmodel.SharedCertificationViewModel
import com.example.client.data.model.viewmodel.mypage.EditLicenseViewModel
import com.example.client.domain.TestUserInfo

@Composable
fun EditLicenseScreen(
    sharedViewModel: SharedCertificationViewModel,
    editLicenseViewModel: EditLicenseViewModel,
    navController: NavController
){
    var nickname by remember { mutableStateOf<String?>(null) }


    val licensesState by sharedViewModel.licensesState.collectAsState()
    val isLoading by sharedViewModel.isLoading.collectAsState()
    val errorMessage by sharedViewModel.errorMessage.collectAsState()

    val selectedLicenses by editLicenseViewModel.selectedLicenses.collectAsState()
    val selectedLicensesCount = selectedLicenses.size

    LaunchedEffect(Unit) {
        nickname = TestUserInfo.TEST_USERNAME
        sharedViewModel.fetchLicenses()
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
                        text = "자격증 수정",
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
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(400.dp)
                            ) {
                                LazyColumn(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    items(licensesState) { license ->
                                        CertificateComponent(
                                            type = license.seriesnm,
                                            name = license.jmfldnm,
                                            date = license.expirationDate ?: "2024-12-05",
                                            isSelected = if(TestUserInfo.LICENSES.contains(license)) true else false,
                                            onItemSelected = { selectedLicense ->
                                                editLicenseViewModel.toggleLicencesSelection(selectedLicense)
                                            },
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(horizontal = 39.dp),
                                        )
                                        Spacer(modifier = Modifier.height(25.dp))
                                    }
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
                }
                Spacer(modifier = Modifier.height(20.dp))
                ButtonComponent(
                    buttonText = if (selectedLicensesCount == 0) "설정하기" else "${selectedLicensesCount}개 추가완료하기",
                    buttonColorType = if (selectedLicensesCount == 0) ButtonColorEnum.LightGreen else ButtonColorEnum.Green,
                    onClick = {
                        editLicenseViewModel.setUserLicenses(selectedLicenses)
                        navController.navigate("MyPage")
                    },
                )
            }
        }
    }
}