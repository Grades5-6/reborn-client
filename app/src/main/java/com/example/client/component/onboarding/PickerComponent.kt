package com.example.client.component.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.client.R

data class MenuItem(
    val name: String,
    val submenus: List<String>
)

@Composable
fun PickerComponent(onSubMenuSelected: (String?) -> Unit) {
    val menuData = listOf(
        MenuItem("서울", listOf("강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구", "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구")),
        MenuItem("경기", listOf("고양시", "광주시", "부천시", "성남시", "수원시", "안양시", "화성시")),
        MenuItem("전라", listOf("광주시", "순천시", "전주시")),
        MenuItem("강원", listOf("강릉시", "원주시", "춘천시"))
    )

    var selectedMenu by remember { mutableStateOf<MenuItem?>(null) }
    var selectedSubMenu by remember { mutableStateOf<String?>(null) }

    Row(
        modifier = Modifier.height(174.dp)
    ) {
        Column(
            modifier = Modifier
                .width(141.dp)
                .fillMaxHeight()
        ) {
            menuData.forEach { menuItem ->
                val backgroundColor =
                    if (selectedMenu == menuItem) Color(0xFF48582F) else Color.Transparent

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundColor)
                    .clickable {
                        selectedMenu = menuItem
                        selectedSubMenu = null
                    }
                    .padding(start = 20.dp, top = 12.dp, end = 12.dp, bottom = 12.dp)
                ) {
                    Text(
                        text = menuItem.name,
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontFamily = FontFamily(Font(R.font.pretendardextrabold)),
                            color = if (selectedMenu == menuItem) Color(0xFFFCF4DC) else Color(0xff61646b),
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }
        }

        // todo : 상위, 하위 모두 선택해야 넘어갈 수 있도록 구현해야 함
        Box(
            modifier = Modifier
                .width(196.dp)
                .fillMaxHeight()
                .background(Color(0xfffffffb))
                .height(192.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.Start
            ) {
                selectedMenu?.submenus?.forEach { submenu ->
                    Box(
                        modifier = Modifier.padding(horizontal = 18.dp, vertical = 8.dp)
                    ) {
                        Text(text = submenu, modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedSubMenu = submenu
                                onSubMenuSelected(submenu)
                            }
                            .padding(8.dp),
                            style = TextStyle(
                                fontSize = 16.sp,
                                lineHeight = 20.sp,
                                fontFamily = FontFamily(Font(R.font.pretendardregular)),
                                fontWeight = if (selectedSubMenu == submenu) FontWeight.ExtraBold else FontWeight.Normal,)
                        )
                    }
                }
            }
        }
    }
}