package com.example.client.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.client.R

data class BarItem (val title :String, val selectIcon: ImageVector, val route:String)

object NavBarItems{
    val BarItems = listOf(
        BarItem(
            title = "홈",
            selectIcon = Icons.Outlined.Home,
            route = "Main"
        ),
        BarItem(
            title = "일자리",
            selectIcon = Icons.Outlined.Build,
            route = "JobMain"
        ),
        BarItem(
            title = "커뮤니티",
            selectIcon = Icons.Outlined.MailOutline,
            route = "Community"
        ),
        BarItem(
            title = "내 정보",
            selectIcon = Icons.Outlined.AccountCircle,
            route = "MyPage"
        )
    )
}