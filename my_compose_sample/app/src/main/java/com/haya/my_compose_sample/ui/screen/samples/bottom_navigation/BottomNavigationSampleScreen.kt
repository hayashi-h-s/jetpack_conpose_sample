package com.haya.my_compose_sample.ui.screen.samples.bottom_navigation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNavigationSampleScreen(toSamples: () -> Unit) {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            Column {
                Button(onClick = {
                    toSamples()
                }) {
                    Text(text = "Samples画面へ")
                }
                Text(color = Color.Red, text = "BottomNavigationサンプル")
            }
        },
        bottomBar = {
            SimpleBottomNavigation(
                navController
            )
        })
    {
        it // itがないとだめ。理由は不明。
        MainScreenNavigation(navController)
    }
}

@Composable
fun SimpleBottomNavigation(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screenItem.forEachIndexed { _, item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = ""
                    )
                },
                label =
                { Text(item.route) },
                selected =
                currentDestination?.hierarchy?.any {
                    it.route == item.route
                } == true,
                unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
                onClick = {
                    navController.navigate(item.route)
                }
            )
        }
    }
}

// sealedで継承を制限
// https://qiita.com/kikuchy/items/ad89a12029082be8d218
sealed class Screen(val route: String, val icon: ImageVector) {
    object MailScreen : Screen("mail", Icons.Filled.Email)
    object ProfileScreen : Screen("profile", Icons.Filled.Person)
}

@Composable
fun BottomSheetWithFloatingButton() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .height(70.dp)
                    .clip(RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp)),
                cutoutShape = androidx.compose.foundation.shape.CircleShape,
                elevation = 20.dp
            ) {
//                CustomBottomNavItem(navController = navController)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                },
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        }
    ) {
        it
        MainScreenNavigation(navController = navController)
    }
}

val screenItem = listOf(
    Screen.MailScreen,
    Screen.ProfileScreen
)

@Composable
fun MainScreenNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.MailScreen.route) {
        //profile
        composable(Screen.MailScreen.route) {
            MailScreen()
        }
        //pickUp
        composable(Screen.ProfileScreen.route) {
            ProfileScreen()
        }
    }
}

@Composable
fun MailScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center),
            text = "Mail",
            color = Color.Red
        )
    }
}

@Composable
fun ProfileScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center),
            text = "profile",
            color = Color.Red
        )
    }
}


