package com.example.client.screen.mypage

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.client.R
import com.example.client.component.all.ButtonColorEnum
import com.example.client.component.all.ButtonComponent
import com.example.client.component.mypage.NicknameFieldComponent
import com.example.client.component.mypage.SelectFieldComponent
import com.example.client.data.model.viewmodel.mypage.EditProfileViewModel
import com.example.client.domain.TestUserInfo
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
fun EditProfileScreen(
    editProfileViewModel: EditProfileViewModel,
    navController: NavController
) {
    val context = LocalContext.current
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var nickname by remember { mutableStateOf<String?>(null) }
    var editNickname by remember { mutableStateOf("") }
    var editEmploymentStatus by remember { mutableStateOf("") }

    // 이미지 선택 launcher
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
        // 선택된 이미지 URI를 TestUserInfo에 저장
        uri?.toString()?.let { TestUserInfo.USERIMG = it }
    }

    LaunchedEffect(Unit) {
        nickname = TestUserInfo.TEST_USERNAME
        // 저장된 이미지 URI가 있다면 로드
        if (TestUserInfo.USERIMG.isNotEmpty()) {
            selectedImageUri = Uri.parse(TestUserInfo.USERIMG)
        }
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
                        text = "프로필 수정",
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
                if (selectedImageUri != null || TestUserInfo.USERIMG.isNotEmpty()) {
                    // 선택된 이미지 또는 저장된 이미지 표시
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(selectedImageUri ?: TestUserInfo.USERIMG)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Selected profile image",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .clickable { launcher.launch("image/*") }
                    )
                } else {
                    // 기본 이미지 표시
                    Image(
                        painter = painterResource(id = R.drawable.icon_rebornlogo),
                        contentDescription = "Profile image",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .clickable { launcher.launch("image/*") }
                    )
                }

                Icon(
                    painter = painterResource(id = R.drawable.outline_insert_photo_24),  // 또는 painterResource(id = R.drawable.your_album_icon)
                    contentDescription = "Change profile image",
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .size(28.dp)
                        .clickable { launcher.launch("image/*") },
                    tint = Color(0xFF48582F)  // 기존 텍스트와 동일한 색상 사용
                )

                Spacer(modifier = Modifier.height(20.dp))
                Column{
                    nickname?.let {
                        NicknameFieldComponent(userName = it) { placeholder ->
                            editNickname = placeholder
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                    SelectFieldComponent(
                        onStatusSelected = { selectedStatus -> editEmploymentStatus = selectedStatus }
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                ButtonComponent(
                    buttonText = "완료",
                    buttonColorType = if (editNickname != "" || editEmploymentStatus != "" || selectedImageUri != null)
                        ButtonColorEnum.Green else ButtonColorEnum.Gray,
                    onClick = {
                        selectedImageUri?.let { uri ->
                            editProfileViewModel.setUserProfile(
                                nickName = editNickname,
                                profileImg = uri.toString(),  // URI 문자열로 전달
                                employmentStatus = editEmploymentStatus
                            )
                        } ?: run {
                            editProfileViewModel.setUserProfile(
                                nickName = editNickname,
                                profileImg = TestUserInfo.USERIMG,
                                employmentStatus = editEmploymentStatus
                            )
                        }
                        navController.navigate("MyPage")
                    }
                )
            }
        }
    }
}