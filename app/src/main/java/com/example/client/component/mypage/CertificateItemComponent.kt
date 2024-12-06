package com.example.client.component.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.client.R
import com.example.client.data.model.response.LicenseResponse

@Composable
fun CertificateItemComponent(license: LicenseResponse) {

    ListItem(
        modifier = Modifier.fillMaxWidth()
            .background(Color(0xFFFFFEF4))
            .border(width = 1.dp, color = Color(0xFFE6E6E6)), // 전체 너비를 채우도록
        headlineContent = {
            Box(
                modifier = Modifier.fillMaxWidth(), // Box를 전체 너비로 설정
                contentAlignment = Alignment.Center // 중앙 정렬
            ) {
                Text(
                    text = license.jmfldnm,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.pretendardregular)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF1D1B20)
                    ),
                    modifier = Modifier.padding(bottom = 8.dp),
                )
            }
        },
        colors = ListItemColors(
            containerColor = Color(0xFFFFFEF4),
            headlineColor = Color(0xFFFFFEF4),
            leadingIconColor = Color(0xFFFFFEF4),
            disabledHeadlineColor = Color(0xFFFFFEF4),
            disabledLeadingIconColor = Color(0xFFFFFEF4),
            disabledTrailingIconColor = Color(0xFFFFFEF4),
            supportingTextColor = Color(0xFFFFFEF4),
            overlineColor = Color(0xFFFFFEF4),
            trailingIconColor = Color(0xFFFFFEF4)
        ),
        supportingContent = {
            Box(
                modifier = Modifier.fillMaxWidth(), // Box를 전체 너비로 설정
                contentAlignment = Alignment.Center // 중앙 정렬
            ) {
                Text(
                    text = license.date.toString(),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.pretendardregular)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF49454F)
                    )
                )
            }
        }
    )

}

//@Preview(showBackground = true)
//@Composable
//fun CertificateItemComponent() {
//    val sample = LicenseResponse(
//        jmfldnm = "문화해설사",
//        seriesnm = "기술사",
//        date = "2022.03.24"
//    )
//
//    CertificateItemComponent(license = sample)
//}