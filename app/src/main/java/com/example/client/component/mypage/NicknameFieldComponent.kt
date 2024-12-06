package com.example.client.component.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.client.R

@Composable
fun NicknameFieldComponent(userName: String, onCancel: (String) -> Unit) {
    var nicknameText by remember { mutableStateOf("") }

    Text(text ="닉네임",
        style = TextStyle(
            fontSize = 24.sp,
            lineHeight = 33.6.sp,
            fontFamily = FontFamily(Font(R.font.pretendardextrabold)),
            color = Color(0xFF000000),
            textAlign = TextAlign.Center,
        )
    )
    Spacer(modifier = Modifier.size(10.dp))
    Row(
        modifier = Modifier.width(320.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        OutlinedTextField(
            value = nicknameText,
            onValueChange = { nicknameText = it },
            placeholder = { Text(userName) },
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.pretendardregular)),
            ),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                unfocusedIndicatorColor = Color.LightGray,
                focusedIndicatorColor = Color.LightGray
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onCancel(nicknameText)
                }
            ),
            trailingIcon = {
                // 화살표 아이콘
                Icon(
                    painter = painterResource(id = R.drawable.outline_cancel_24),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable { nicknameText = "" }
                        .padding(8.dp)
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNicknameFieldComponent() {
    NicknameFieldComponent(
        userName = "사용자 이름",
        onCancel = { nickname ->
            // 여기에 nickname을 처리하는 로직을 추가할 수 있습니다.
            println("닉네임: $nickname")
        }
    )
}
