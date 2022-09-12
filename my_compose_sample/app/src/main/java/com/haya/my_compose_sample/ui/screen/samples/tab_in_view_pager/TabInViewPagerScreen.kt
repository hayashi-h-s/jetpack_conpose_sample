package com.haya.my_compose_sample.ui.screen.samples.tab_in_view_pager

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabInViewPagerScreen(toSamples: () -> Unit) {
    var tabPage by remember { mutableStateOf(TabPage.Home) }
    val pagerState = rememberPagerState(pageCount = TabPage.values().size)
    val scope = rememberCoroutineScope()
    Scaffold(topBar = {
        TabHome(
            selectedTabIndex = pagerState.currentPage,
            // 通常のTab&Pager
//            onSelectedTab = { it.ordinal },
            // タブをアニメーションにする場合
            onSelectedTab = { scope.launch { pagerState.animateScrollToPage(it.ordinal) } },
        )
    }) {
        HorizontalPager(state = pagerState, Modifier.padding(it)) { index ->
            Column(modifier = Modifier.fillMaxSize()) {
                Button(onClick = {
                    toSamples()
                }) {
                    Text(text = "Samples画面へ")
                }
                Text("TabInViewPagerサンプル")
                when (index) {
                    0 -> Text(TabPage.values()[index].name)
                    1 -> Screen()
                    2 -> Text(TabPage.values()[index].name)
                }
            }
        }

    }
}


@Composable
fun Screen() {
    Surface(modifier = Modifier.size(100.dp), color = Color.Red) {

    }
}

enum class TabPage(val icon: ImageVector) {
    Home(Icons.Filled.Home),
    Favorite(Icons.Filled.Favorite),
    Messages(Icons.Filled.Email),
}


@Composable
fun TabHome(selectedTabIndex: Int, onSelectedTab: (TabPage) -> Unit) {
    TabRow(selectedTabIndex = selectedTabIndex, indicator = {
        TabIndicator(
            tabPosition = it,
            index = selectedTabIndex
        )
    }) {
        TabPage.values().forEachIndexed { index, tabPage ->
            Tab(
                selected = index == selectedTabIndex,
                onClick = { onSelectedTab(tabPage) },
                text = { Text(text = tabPage.name) },
                icon = { Icon(imageVector = tabPage.icon, contentDescription = null) },
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.Blue
            )
        }
    }
}

@Composable
fun TabIndicator(tabPosition: List<TabPosition>, index: Int) {
    val transition = updateTransition(targetState = index, label = "") // ?
    val leftIndicator by transition.animateDp(label = "", transitionSpec = {
        spring(stiffness = Spring.StiffnessLow) // ?
    }) {
        tabPosition[it].left
    }
    val rightIndicator by transition.animateDp(label = "", transitionSpec = {
        spring(stiffness = Spring.StiffnessMediumLow) // ?
    }) {
        tabPosition[it].right
    }
    // アニメーションの場合は使用しない
    val width = tabPosition[index].width
    val offsetX = tabPosition[index].left
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomStart)
            .offset(x = leftIndicator)
//            .offset(offsetX)
            .width(rightIndicator - leftIndicator)
//            .width(width)
            .padding(4.dp)
            .fillMaxSize()
            .border(
                BorderStroke(2.dp, Color.Green),
                RoundedCornerShape(4.dp)
            )
    )

}

