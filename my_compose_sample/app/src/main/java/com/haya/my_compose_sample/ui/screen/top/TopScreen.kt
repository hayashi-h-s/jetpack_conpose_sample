package com.haya.my_compose_sample.ui.screen.top

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.haya.my_compose_sample.R
import com.haya.my_compose_sample.ui.theme.My_compose_sampleTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TopScreen(toDetail: () -> Unit, toSamples: () -> Unit) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            // 参考サイト https://techbooster.org/android/resource/18628/
//        fun TopAppBar(
//            title: @Composable () -> Unit,
//            modifier: Modifier = Modifier,
//            navigationIcon: @Composable (() -> Unit)? = null,
//            actions: @Composable RowScope.() -> Unit = {},
//            backgroundColor: Color = MaterialTheme.colors.primarySurface,
//            contentColor: Color = contentColorFor(backgroundColor),
//            elevation: Dp = AppBarDefaults.TopAppBarElevation
//        ) {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) },
                modifier = Modifier.height(100.dp),
                navigationIcon = {
                    IconButton(onClick = { /* クリック処理 */ }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Open drawer")
                    }
                },
                actions = {
                    IconButton(onClick = { /* クリック処理 */ }) {
                        Icon(Icons.Filled.Edit, contentDescription = "Edit text")
                    }
                    IconButton(onClick = { /* クリック処理 */ }) {
                        Icon(Icons.Filled.Share, contentDescription = "Share text")
                    }
                },
                backgroundColor = MaterialTheme.colors.primarySurface,
                elevation = AppBarDefaults.TopAppBarElevation
            )
        },
        // 参考サイト https://techbooster.org/android/ui/18533/
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Toast表示参考サイト
                    Toast.makeText(context, "FloatingActionButtonをクリック", Toast.LENGTH_LONG).show()


                },
                backgroundColor = Color.Red,
            ) {
                Icon(Icons.Filled.Add, contentDescription = "追加")
            }
        },
    ) {}
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) { // スクロール可能に
        Text(
            text = "Androidサンプルアプリ",
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .background(
                    Color(red = 0, green = 255, blue = 0)
                )
                .padding(all = 16.dp),
            fontSize = 25.sp,
            color = Color(red = 0f, green = 0f, blue = 1f)
//            color = Color(red = 0, green = 255, blue = 0)
//            color = Color(0xFFFF0000)
        )
        Row {
            Button(
                onClick = {
                    toDetail()
                },
                Modifier
                    .padding(top = 0.dp, start = 8.dp)
                    .width(100.dp)
            ) {
                Text(text = "詳細へ")
            }
            Button(
                onClick = {
                    toSamples()
                },
                Modifier
                    .padding(top = 0.dp, start = 8.dp)
                    .height(50.dp)
            ) {
                Text(text = "サンプル一覧へ")
            }
        }

        Column {
            //MEMO: → Rのimportはこれを追加 import com.haya.my_compose_sample.R
            Column(Modifier.padding(all = 8.dp)) {
                Text("ローカルのImageを表示")
                Image(
                    painter = painterResource(id = R.drawable.ic_android_icon),
                    contentDescription = "android_icon",
                    Modifier
                        .padding(all = 4.dp)
                        .width(120.dp)
                        .height(120.dp)
                )
            }
            Column(Modifier.padding(all = 8.dp)) {
                val painter = rememberImagePainter( // Coilを使用した画像表示
                    data = "https://picsum.photos/300/300",
                    builder = {
                        crossfade(true)
                    }
                )
                Box {
                    Image(
                        painter = painter,
                        contentDescription = "android_image",
                        Modifier
                            .padding(all = 4.dp)
                            .width(120.dp)
                            .height(120.dp)
                    )
                    when (painter.state) {
                        is ImagePainter.State.Loading -> {
                            CircularProgressIndicator(Modifier.align(Alignment.Center)) // 中央
                        }
                        is ImagePainter.State.Error -> {
                            // エラー時の処理
                        }
                        else -> {}
                    }
                }
            }

            // Loading
            // 参考→サンプルあり https://techbooster.org/android/ui/18545/
            CircularProgressIndicator(modifier = Modifier.padding(all = 8.dp))
            LinearProgressIndicator(modifier = Modifier.padding(all = 8.dp))
        }

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    My_compose_sampleTheme {
        TopScreen({ }, { })
    }
}