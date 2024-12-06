package com.example.client.component.all

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.client.R
import com.example.client.data.model.response.LicensesGetResponse

@Composable
fun CertificateComponent(
    type: String,
    name: String,
    date: String,
    onItemSelected:(LicensesGetResponse)->Unit,
    isSelected:Boolean,
    modifier: Modifier = Modifier
) {
    val buttonColorType = if (isSelected) ButtonColorEnum.Green else ButtonColorEnum.LightGreen
    Box(
        modifier = Modifier
            .then(modifier)
            .clickable { onItemSelected(LicensesGetResponse(jmfldnm = name, seriesnm = type, expirationDate = date)) }
            .shadow(elevation = 4.dp, spotColor = Color(0x40000000), ambientColor = Color(0x40000000))
            .width(312.dp)
            .height(119.dp)
            .background(color = buttonColorType.color, shape = RoundedCornerShape(size = 20.dp))
    ) {

        Text(
            text = type,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFFDCF5B5),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 18.dp, top = 14.dp)
        )
        Text(
            text = name,
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFFFFFBDC),
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 18.dp, top = 40.dp)
        )

        Image(
            painter = painterResource(R.drawable.icon_certificate),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 17.dp, end = 18.dp)
        )

        Text(
            text = date,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFFF5F5F5),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 11.dp, bottom = 11.dp
                )
        )
    }
}
