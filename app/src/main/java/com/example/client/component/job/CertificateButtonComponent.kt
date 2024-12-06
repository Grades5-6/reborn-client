package com.example.client.component.job

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun CertificateButtonComponent(
    certificateDepartment: String,
    certificateName: String,
    date: String,
    onClick: () -> Unit
) {
    var isOverlayVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(8.dp)
            .height(126.dp)
            .width(155.dp)
            .shadow(8.dp, RoundedCornerShape(24.dp))
            .background(color = Color(0xFF8FA86A), shape = RoundedCornerShape(20.dp))
            .clickable {
                if (isOverlayVisible) { onClick() }
                isOverlayVisible = !isOverlayVisible
                       },
        contentAlignment = Alignment.TopStart
    ) {
        // 기본 내용
        Column(
            modifier = Modifier.padding(13.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = certificateDepartment,
                color = Color(0xFFDCF5B5),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = certificateName,
                color = Color(0xFFFFFBDC),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_certificate),
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = date,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        if (isOverlayVisible) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xAA000000), shape = RoundedCornerShape(8.dp))
            ) {
                Text(
                    text = "관련 공고\n확인하기",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

/*
            CertificateButtonComponent(
                certificateDepartment = "한국고용정보원",
                certificateName = "고용서비스",
                date = "25.07.15",
                onClick = {
                    Log.i("ComponentTag", "CertificateButtonComponent")
                }
            )
*/