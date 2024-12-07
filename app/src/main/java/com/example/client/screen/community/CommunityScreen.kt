package com.example.client.screen.community

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.client.R
import com.example.client.component.all.ButtonColorEnum
import com.example.client.component.all.ButtonComponent
import com.example.client.component.all.TabLayoutComponent
import com.example.client.component.community.FloatingButtonComponent
import com.example.client.component.community.OptionList
import com.example.client.component.community.OptionListComponent
import com.example.client.component.community.Post
import com.example.client.component.community.PostListItemComponent

@Composable
fun CommunityScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFBDC))
    ) {
        Column {
            Row(
                modifier = Modifier.padding(start = 15.dp, top = 40.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_rebornlogo),
                    contentDescription = "Icon_rebornlogo",
                    modifier = Modifier
                        .width(80.dp)
                        .height(66.dp)
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(color = Color(0xFFFFFEF4), shape = RoundedCornerShape(size = 40.dp))
            ) {
                item {
                    TabLayoutComponent(tabs = listOf("전체", "동네 이야기")) { page ->
                        if (page == 0) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = "관심사",
                                    fontSize = 15.sp,
                                    fontFamily = FontFamily(Font(R.font.pretendardextrabold)),
                                    modifier = Modifier
                                        .padding(4.dp)
                                )
                                OptionListComponent(
                                    optionList = OptionList(
                                        options = listOf(
                                            "전체보기",
                                            "교육",
                                            "상담",
                                            "농업",
                                            "미디어",
                                            "서비스",
                                            "IT",
                                            "택배",
                                            "매장관리"
                                        )
                                    ),
                                    modifier = Modifier.height(110.dp)
                                )
                                Text(
                                    text = "구분",
                                    fontSize = 15.sp,
                                    fontFamily = FontFamily(Font(R.font.pretendardextrabold)),
                                    modifier = Modifier
                                        .padding(4.dp)
                                )
                                OptionListComponent(
                                    optionList = OptionList(
                                        options = listOf(
                                            "전체보기",
                                            "공고",
                                            "고민 상담",
                                            "후기",
                                            "소모임",
                                            "정보공유",
                                        )
                                    ),
                                    modifier = Modifier.height(80.dp)
                                )
                                Button(
                                    onClick = { },
                                    shape = RoundedCornerShape(20.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = ButtonColorEnum.Green.color),
                                    modifier = Modifier
                                        .height(50.dp)
                                        .width(160.dp)
                                        .padding(4.dp)
                                        .align(Alignment.CenterHorizontally)
                                ) {
                                    Text(
                                        text = "검색",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center
                                    )
                                }

                            }
                        }
                    }

                }
                item {
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    PostListItemComponent(
                        post = Post(
                            author = "감자도리",
                            title = "매장쪽 질문",
                            content = "지금까지",
                            likes = 8,
                            comments = 19
                        )
                    )
                    PostListItemComponent(
                        post = Post(
                            author = "감자도리",
                            title = "매장쪽 질문",
                            content = "지금까지",
                            likes = 8,
                            comments = 19
                        )
                    )
                    PostListItemComponent(
                        post = Post(
                            author = "감자도리",
                            title = "매장쪽 질문",
                            content = "지금까지",
                            likes = 8,
                            comments = 19
                        )
                    )
                    PostListItemComponent(
                        post = Post(
                            author = "감자도리",
                            title = "매장쪽 질문",
                            content = "지금까지",
                            likes = 8,
                            comments = 19
                        )
                    )
                    PostListItemComponent(
                        post = Post(
                            author = "감자도리",
                            title = "매장쪽 질문",
                            content = "지금까지",
                            likes = 8,
                            comments = 19
                        )
                    )
                    PostListItemComponent(
                        post = Post(
                            author = "감자도리",
                            title = "매장쪽 질문",
                            content = "지금까지",
                            likes = 8,
                            comments = 19
                        )
                    )
                    PostListItemComponent(
                        post = Post(
                            author = "감자도리",
                            title = "매장쪽 질문",
                            content = "지금까지",
                            likes = 8,
                            comments = 19
                        )
                    )
                }
            }
        }
        Image(
            painter = painterResource(id = R.drawable.icon_fab),
            contentDescription = null,
            modifier = Modifier
                .size(130.dp)
                .fillMaxWidth()
                .align(Alignment.BottomEnd)
                .padding(15.dp)
        )
    }
}


@Preview
@Composable
fun CommunityScreenPreview() {
    CommunityScreen()
}
