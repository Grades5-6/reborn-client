package com.example.client.component.all

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import com.example.client.R
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.text.input.ImeAction

@Composable
fun SearchComponent(
    searchPlaceholderText: String,
    onClickCancel: () -> Unit,
    onSearch: (String) -> Unit
) {
    var searchText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xAA000000))
    ) {

        Box(
            modifier = Modifier
                .padding(8.dp)
                .height(170.dp)
                .width(348.dp)
                .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                .border(2.dp, Color.LightGray, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.TopStart
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_cancel),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        onClickCancel()
                    }
                    .align(Alignment.TopEnd)
            )

            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    placeholder = { Text(searchPlaceholderText) },
                    modifier = Modifier
                        .height(52.dp)
                        .width(264.dp)
                        .background(Color.LightGray, shape = RoundedCornerShape(8.dp)),
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
                            onSearch(searchText)
                        }
                    )
                )
            }
        }
    }
}
/*
            SearchComponent(
                searchPlaceholderText = "입력하세요",
                onClickCancel = {
                    Log.i("ComponentTag", "SearchComponent Cancel Click")
                }
                onSearch = { 
                    Log.i("ComponentTag", "SearchComponent Search Enter")
                }
            )
            
            검색 버튼이 없어, Enter 버튼 입력 시 OnSearch 함수가 실행됨
 */