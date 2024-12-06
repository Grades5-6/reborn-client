package com.example.client.component.community

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.client.R

data class OptionList(
    val options: List<String> // 리스트를 받아옵니다
)

@Composable
fun OptionListComponent(optionList: OptionList) {
    var selectedField by remember { mutableStateOf<String?>(null) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.fillMaxSize(),
    ) {
        items(optionList.options) { option ->
            val isSelected = selectedField == option
            val backgroundColor = if (isSelected) Color(0xFF48582F) else Color.Transparent

            Box(modifier = Modifier
                .padding(4.dp)
                .border(
                    width = 0.71.dp,
                    color = Color(0xFF48582F),
                    shape = RoundedCornerShape(size = 20.dp)
                )
                .background(backgroundColor, shape = RoundedCornerShape(size = 20.dp))
                .clickable {
                    selectedField = if (isSelected) null else option
                }
                .padding(4.dp)
                .width(51.dp),
                contentAlignment = Alignment.Center) {
                Text(
                    text = option,
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

@Preview(showBackground = true)
@Composable
fun PreviewCommunityOptionListComponent() {
    val sampleOptions = OptionList(options = listOf("전체보기", "교육", "상담", "농업", "미디어"))
    OptionListComponent(optionList = sampleOptions)
}