package com.example.client.component.job

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.client.R

@Composable
fun LoadingComponent(
    name : String
){
    val message = stringResource(R.string.loading_message,name)
    val annotatedLoadingMessage = buildAnnotatedString {
        val parts = message.split(name)
        append(parts[0])
        withStyle(SpanStyle(fontWeight = FontWeight.Bold)){
            append(name)
        }
        if (parts.size>1){
            append(parts[1])
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_loading),
            contentDescription = null
        )
        Spacer(Modifier.height(28.dp))
        Text(
            text = annotatedLoadingMessage,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}


@Preview
@Composable
fun LoadingComponentPreview(){
    Column(
        Modifier.background(color = Color.White)
    ) {
        LoadingComponent(
            name = "조휘원"
        )
    }
}

