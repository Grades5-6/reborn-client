package com.example.client.screen.community

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.client.R
import com.example.client.component.all.DropDownMenuComponent
import com.example.client.component.community.OptionList
import com.example.client.component.community.OptionListComponent

@Composable
fun CommunityWriteScreen(
    //viewModel: CommunityViewModel,
    //navController: NavController
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFFFFFEF4), shape = RoundedCornerShape(size = 40.dp)
                )
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .background(color = Color(0xFFFFFBDC)),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, top = 20.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_rebornlogo),
                        contentDescription = "Icon_rebornlogo",
                        modifier = Modifier
                            .width(80.dp)
                            .height(66.dp)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xFFFFFEF4), shape = RoundedCornerShape(size = 40.dp))){
                Text(text = "글쓰기",
                    modifier = Modifier
                        .padding(top = 30.dp, start = 20.dp),
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.pretendardextrabold)),
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp
                    )
                )
                Row(
                    modifier = Modifier.padding(top = 30.dp, start = 20.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text = "지역",
                        modifier = Modifier
                            .padding(end = 20.dp),
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.pretendardregular)),
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp
                        )
                    )
                    DropDownMenuComponent(onItemSelected = {},
                        list = listOf(
                            "1960",
                            "1961",
                            "1962",
                            "1963",
                            "1964",
                            "1965",
                            "1966",
                            "1967",
                            "1968",
                            "1969"
                        ))
                }
                Row(
                    modifier = Modifier.padding(top = 20.dp, start = 20.dp, end=15.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text = "업종",
                        modifier = Modifier
                            .padding(end = 15.dp),
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.pretendardregular)),
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp
                        )
                    )
                    val sampleOptions = OptionList(options = listOf("전체보기", "교육", "상담", "농업", "미디어"))
                    OptionListComponent(optionList = sampleOptions)
                }
                Row(
                    modifier = Modifier.padding(top = 20.dp, start = 20.dp, end=15.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text = "구분",
                        modifier = Modifier
                            .padding(end = 15.dp),
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.pretendardregular)),
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                            fontSize = 20.sp
                        )
                    )
                    val sampleOptions = OptionList(options = listOf("전체보기", "교육", "상담", "농업", "미디어"))
                    OptionListComponent(optionList = sampleOptions)
                }
            }
        }
    }
}

@Preview
@Composable
fun CommunityWritePreviewScreen() {
    CommunityWriteScreen()
}