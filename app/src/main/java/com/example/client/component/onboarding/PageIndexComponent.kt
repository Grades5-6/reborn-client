package com.example.client.component.onboarding

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PageIndexComponent(
    currentPage: Int,
    totalPage: Int,
    modifier: Modifier = Modifier
) {
    Row(modifier = Modifier
        .then(modifier)
        .padding(8.dp)) {
        Text(
            text = "$currentPage",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
        )

        Text(
            text = "/$totalPage",
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        )
    }
}
/*
    var currentPage by remember { mutableIntStateOf(1) }
    var totalPage = 3

    PageIndexComponent(currentPage, totalPage)

    외부에서 currentPage 값을 변경 시, 따라서 변경
*/