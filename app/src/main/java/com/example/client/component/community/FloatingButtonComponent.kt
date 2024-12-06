package com.example.client.component.community

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.*
import com.example.client.R

@Composable
fun FloatingButtonComponent(
    onClick: () -> Unit,
) {
    Surface(
        modifier = Modifier
            .size(120.dp) // FAB의 크기
            .clickable(onClick = onClick),
        shape = CircleShape,
    ) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.icon_fab),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFloatingButtonComponent() {
    FloatingButtonComponent(
        onClick = { /* 클릭 시 동작 */ }
    )
}