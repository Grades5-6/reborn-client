package com.example.client.component.job

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.client.R
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun JobListComponent(
        companyName: String,
        jobTitle: String,
        location: String,
        onClick: () -> Unit
    ) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .height(128.dp)
            .width(330.dp)
            .shadow(8.dp, RoundedCornerShape(24.dp))
            .background(color = Color(0xFFFFFBDC), shape = RoundedCornerShape(20.dp)),
        contentAlignment = Alignment.TopStart
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFFBDC)) // 카드 배경색
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 왼쪽 텍스트 영역
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                // 회사명
                Text(
                    text = companyName,
                    color = Color(0xFF757575),
                    fontSize = 14.sp,
                    minLines = 1,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(3.dp))
                // 업무명
                Text(
                    text = jobTitle,
                    color = Color(0xFF366943),
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.pretendardextrabold)),
                    fontWeight = FontWeight.ExtraBold,
                    minLines = 1,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(4.dp))
                // 위치
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_location), // 위치 아이콘
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = location,
                        color = Color.Black,
                        fontSize = 16.sp,
                        minLines = 1,
                        maxLines = 1
                    )
                }
            }

            // 오른쪽 버튼 영역
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxHeight()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_green_star), // 즐겨찾기 아이콘
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color(0xFF687F45) // 기본은 회색
                )
                Spacer(modifier = Modifier.height(14.dp))
                Button(
                    onClick = onClick,
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF687F45) // 버튼 배경색
                    ),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                    modifier = Modifier
                        .height(40.dp)
                        .width(100.dp)
                        .padding(4.dp)
                ) {
                    Text(
                        text = "자세히 보기",
                        fontSize = 12.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun JobListComponentPreview(){
    JobListComponent(
        companyName = "사단법인 인구보건복지협회",
        jobTitle = "2025년 제1차 인구보건복지협회  신규직원 인천지회(방사선사) 모집 공고",
        location = "07230 서울특별시 영등포구 버드나루로14가길 20 (당산동))",
        onClick = {
            // 클릭 이벤트 처리
        }
    )
}