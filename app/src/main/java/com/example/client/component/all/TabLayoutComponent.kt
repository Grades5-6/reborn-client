package com.example.client.component.all

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabLayoutComponent(
    tabs: List<String> = listOf("전체", "동네이야기"),
    paddingValues: PaddingValues = PaddingValues(0.dp),
    content: @Composable (Int) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val selectedIndex = remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState(
        pageCount = { tabs.size },
        initialPageOffsetFraction = 0f,
        initialPage = 0,
    )
    val tabIndex = pagerState.currentPage
    Column(
        modifier = Modifier.padding(paddingValues)
    ) {
        SecondaryTabRow(
            selectedTabIndex = tabIndex,
            containerColor = Color.Transparent,
            indicator = {
                TabRowDefaults.SecondaryIndicator(
                    Modifier
                        .tabIndicatorOffset(tabIndex, matchContentSize = true),
                    color = Color(0xFF366943)
                )
            }
        ) {
            tabs.forEachIndexed { index, value ->
                Tab(
                    selected = tabIndex == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    modifier = Modifier
                        .padding(top = 10.dp)
                ) {
                    Text(
                        text = value,
                        color = if (tabIndex == index) Color.Black else Color.Gray,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 7.dp)
                    )
                }
            }
        }

        HorizontalPager(state = pagerState, userScrollEnabled = true) { page ->
            content(page)
        }
    }
}

@Preview
@Composable
fun TabLayoutComponentPreview() {
    TabLayoutComponent(tabs = listOf("전체", "동네이야기")) { page ->
        when (page) {
            0 -> {}
            1 -> {}
        }
    }
}