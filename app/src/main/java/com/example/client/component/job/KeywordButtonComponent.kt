package com.example.client.component.job

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KeywordButtonComponent(
    buttonText: String,
    onClick: () -> Unit
){
    Button(
        // TODO : 버튼 클릭
        onClick = {},
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF688142)),
        shape = RoundedCornerShape(20.dp),
    ) {
        Text(
            text = buttonText,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 1.dp),
            color = Color(0xFFFFFBDC),
            maxLines = 1,
            minLines = 1,
            fontSize = 17.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun KeywordButtonComponentPreview(){
    KeywordButtonComponent(
        buttonText = "서울 중구",
        onClick = {}
    )
}