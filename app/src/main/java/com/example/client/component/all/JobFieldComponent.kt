package com.example.client.component.all

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.client.R

@Composable
fun JobFieldComponent(onFieldsSelected: (List<JobField>) -> Unit) {

    val jobFields = getJobFields()

    val selectedFields = remember { mutableStateListOf<JobField>() }

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.height(220.dp),
        contentPadding = PaddingValues(40.dp)
    ) {
        items(jobFields.size) { index ->
            val jobField = jobFields[index]
            val isSelected = selectedFields.contains(jobField)
            val backgroundColor = if (isSelected) Color(0xFF48582F) else Color.Transparent

            Box(modifier = Modifier
                .padding(5.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFF48582F),
                    shape = RoundedCornerShape(size = 20.dp)
                )
                .background(backgroundColor, shape = RoundedCornerShape(size = 20.dp))
                .clickable {
                    if (isSelected) {
                        selectedFields.remove(jobField) // 이미 선택된 경우 선택 해제
                    } else {
                        selectedFields.add(jobField) // 선택되지 않은 경우 추가
                    }
                    onFieldsSelected(selectedFields) // 선택된 필드 리스트를 전달
                }
                .padding(10.dp),
                contentAlignment = Alignment.Center) {
                Text(
                    text = jobField.name,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.pretendardregular)),
                        color = if (isSelected) Color.White else Color.Black
                    )
                )
            }
        }
    }
}
