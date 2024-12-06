package com.example.client.component.community

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.client.R

data class Post(
    val author: String,
    val title: String,
    val content: String,
    val likes: Int,
    val comments: Int
)

@Composable
fun PostListItemComponent(post: Post) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardColors(
            containerColor = Color(0xFFFFFEF4),
            contentColor = Color(0xFFFFFEF4),
            disabledContentColor = Color(0xFFFFFEF4),
            disabledContainerColor = Color(0xFFFFFEF4)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = post.author,
                style = TextStyle(fontSize = 13.69.sp,
                    fontFamily = FontFamily(Font(R.font.pretendardextrabold)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFF366943)),
                modifier = Modifier.padding(bottom = 4.dp),

            )

            Text(
                text = post.title,
                style = TextStyle(fontSize = 17.11.sp,
                    fontFamily = FontFamily(Font(R.font.pretendardregular)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000)),
                modifier = Modifier.padding(bottom = 10.dp)
            )

            Text(
                text = post.content,
                style = TextStyle(
                    fontSize = 15.97.sp,
                    fontFamily = FontFamily(Font(R.font.pretendardregular)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                ),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "좋아요 ${post.likes}",
                    style = TextStyle(
                        fontSize = 13.69.sp,
                        fontFamily = FontFamily(Font(R.font.pretendardregular)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFFB3B3B3)
                    )
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(text = "댓글 ${post.comments}",
                    style = TextStyle(
                        fontSize = 13.69.sp,
                        fontFamily = FontFamily(Font(R.font.pretendardregular)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFFB3B3B3)
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPostListItemComponent() {
    val samplePost = Post(
        author = "작성자 이름",
        title = "게시글 제목",
        content = "여기에 게시글 내용이 들어갑니다.",
        likes = 10,
        comments = 5
    )
    PostListItemComponent(post = samplePost)
}
