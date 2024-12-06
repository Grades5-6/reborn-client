package com.example.client.component.all

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class ButtonColorEnum(val color: Color) {
    LightGreen(Color(0xFF93B65E)),
    Green(Color(0xFF47572F)),
    Gray(Color(0xFFD9D9D9))
}

@Composable
fun ButtonComponent(
    buttonText: String,
    buttonColorType: ButtonColorEnum,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = buttonColorType.color),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .then(modifier)
            .height(50.dp)
            .width(288.dp)
            .padding(4.dp)
    ) {
        Text(
            text = buttonText,
            color = Color(0xFFFCF4DC),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}
/*
        ButtonComponent(
            buttonText = "String Button Text",
            buttonColorType = ButtonColorEnum.LightGreen,//or Green
            onClick = { Log.i("ComponentTag", "ButtonComponent") }
        )
*/
