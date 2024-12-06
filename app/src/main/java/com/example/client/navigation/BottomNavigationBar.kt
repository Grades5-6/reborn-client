package com.example.client.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.client.R

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar(
        modifier = Modifier
            .background(color = Color.White),
        contentColor = colorResource(id = android.R.color.white),
        containerColor = colorResource(id = android.R.color.white),
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        if(!(currentRoute.equals("Login") || currentRoute.equals("MainOnboarding"))){
            NavBarItems.BarItems.forEach { navItem ->
                NavigationBarItem(
                    modifier = Modifier.background(color = Color.White),
                    selected = currentRoute == navItem.route,
                    onClick = {
                        navController.navigate(navItem.route)
                        {
                            popUpTo(navController.graph.findStartDestination().id) {
                            }
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = navItem.selectIcon,
                            contentDescription = navItem.title,
                            modifier = Modifier.size(32.dp)
                        )
                    },
                    label = {
                        Text(text = navItem.title,
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 15.64.sp,
                                fontFamily = FontFamily(Font(R.font.pretendardregular)),
                                fontWeight = FontWeight(500),
                                textAlign = TextAlign.Center,
                            )
                        )
                    },
                    colors = NavigationBarItemColors(
                        selectedIconColor = Color(color = 0xFF93B65E),
                        unselectedIconColor = Color.Black,
                        disabledIconColor = Color.Black,
                        selectedIndicatorColor = Color.White,
                        disabledTextColor = Color.Black,
                        unselectedTextColor = Color.Black,
                        selectedTextColor = Color(color = 0xFF93B65E)
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    val navController = rememberNavController()
    BottomNavigationBar(navController = navController)
}
