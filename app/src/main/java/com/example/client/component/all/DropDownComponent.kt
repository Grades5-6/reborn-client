package com.example.client.component.all

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DropDownMenuComponent(
    list: List<String>,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val expandStatus = remember { mutableStateOf(false) }
    val itemPosition = remember { mutableIntStateOf(0) }

    Box(
        modifier = Modifier
            .then(modifier)
    ) {
        DropDownBox(
            modifier = Modifier
                .clickable {
                    expandStatus.value = true
                },
            text = list[itemPosition.intValue]
        )
        DropdownMenu(
            expanded = expandStatus.value,
            onDismissRequest = {
                expandStatus.value = false
            },
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
                .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp))
        ) {
            list.forEachIndexed { index, item ->
                DropdownMenuItem(
                    text = {
                        Text(text = item)
                    },
                    onClick = {
                        expandStatus.value = false
                        itemPosition.intValue = index
                        onItemSelected(item)
                    },
                    modifier = Modifier
                        .background(
                            color = Color.White,
                        )
                )
            }
        }
    }
}

@Composable
fun DropDownBox(
    modifier: Modifier = Modifier,
    text: String,
) {
    Box(
        modifier = Modifier
            .then(modifier)
            .size(width = 300.dp, height = 50.dp)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
    ) {
        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 10.dp)
        )
        Text(
            text = text,
            fontSize = 15.sp,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 10.dp)
        )
    }
}


@Preview
@Composable
fun DropDownMenuPreview() {
    DropDownMenuComponent(
        onItemSelected = {},
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
        )
    )
}

@Preview
@Composable
fun DropDownBoxPreview() {
    DropDownBox(
        text = "hi"
    )
}