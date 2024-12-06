package com.example.client.component.all

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.client.R
import androidx.compose.ui.text.TextStyle


@Composable
fun CertificateListComponent(
    certificateList: List<String>,
) {
    Column(
        modifier = Modifier
            .wrapContentSize(Alignment.Center) // 내부 콘텐츠를 화면 가운데 정렬
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally // 자식 컴포넌트를 가운데 정렬
    ) {
        certificateList.forEach { item ->
            Text(
                text = item,
                modifier = Modifier.padding(vertical = 10.dp), // 각 항목 간격 조정
                fontWeight = FontWeight.Bold, // 볼드 추가
                textAlign = TextAlign.Center, // 텍스트 가운데 정렬
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.pretendardregular)),)
            )
        }
    }
}