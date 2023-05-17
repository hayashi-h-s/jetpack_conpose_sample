package com.haya.my_compose_sample.presentation.util

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSpec
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import com.haya.my_compose_sample.R
import com.haya.my_compose_sample.presentation.util.route.MainRoute
import com.haya.my_compose_sample.presentation.util.route.SampleRouteType
import com.haya.my_compose_sample.ui.screen.detail.DetailScreen
import com.haya.my_compose_sample.ui.screen.detail.DetailViewModel
import com.haya.my_compose_sample.ui.screen.main.MainScreen
import com.haya.my_compose_sample.ui.screen.samples.SamplesScreen
import com.haya.my_compose_sample.ui.screen.samples.bottom_navigation.BottomNavigationSampleScreen
import com.haya.my_compose_sample.ui.screen.samples.bottom_sheet.BottomSheetSampleScreen
import com.haya.my_compose_sample.ui.screen.samples.constraint_layout.ConstraintLayoutScreen
import com.haya.my_compose_sample.ui.screen.samples.conter.CounterScreen
import com.haya.my_compose_sample.ui.screen.samples.custom_view.CustomViewScreen
import com.haya.my_compose_sample.ui.screen.samples.disposable_effect.DisposableEffectScreen
import com.haya.my_compose_sample.ui.screen.samples.exo_player.ExoPlayerSampleScreen
import com.haya.my_compose_sample.ui.screen.samples.exo_player.ExoPlayerViewModel
import com.haya.my_compose_sample.ui.screen.samples.launched_effect.LaunchedEffectScreen
import com.haya.my_compose_sample.ui.screen.samples.launched_effect.LaunchedEffectViewModel
import com.haya.my_compose_sample.ui.screen.samples.list_view.ListViewScreen
import com.haya.my_compose_sample.ui.screen.samples.message_list.MessageListScreen
import com.haya.my_compose_sample.ui.screen.samples.preview_parameter.PreviewParameterScreen
import com.haya.my_compose_sample.ui.screen.samples.preview_parameter.models.ExampleUiState
import com.haya.my_compose_sample.ui.screen.samples.preview_parameter.models.Memo
import com.haya.my_compose_sample.ui.screen.samples.room_sample.RoomSampleScreen
import com.haya.my_compose_sample.ui.screen.samples.rotation_animation.RotationAnimationScreen
import com.haya.my_compose_sample.ui.screen.samples.tab_in_view_pager.TabInViewPagerScreen
import com.haya.my_compose_sample.ui.screen.samples.text_field.TextFieldScreen
import kotlinx.coroutines.runBlocking

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainRoute.MainScreen.route
    ) {
        composable(route = MainRoute.MainScreen.route) {
            MainScreen(
                { navController.navigate("detail") },
                { navController.navigate(route = MainRoute.SamplesScreen.route) }
            )
        }
        composable("detail") {
            val vm: DetailViewModel = viewModel()
            DetailScreen(vm) {
                navController.navigate(route = MainRoute.MainScreen.route)
            }
        }
        //samples start region
        composable(route = MainRoute.SamplesScreen.route) {
            SamplesScreen(
                backAction = { navController.popBackStack() },
                toSampleDetail = { route ->
                    navController.navigate(route = route)
                },
            )
        }

        SampleRouteType.values().forEach { routeType ->
            composable(routeType.route) {
                when (routeType) {
                    SampleRouteType.REMEMBER_SCREEN -> CounterScreen { navController.popBackStack() }
                    SampleRouteType.TEXT_FIELD_SCREEN -> TextFieldScreen { navController.popBackStack() }
                    SampleRouteType.LAZY_COLUMN_SCREEN -> ListViewScreen { navController.popBackStack() }
                    SampleRouteType.LAUNCHED_EFFECT_SCREEN -> {
                        val vm: LaunchedEffectViewModel = viewModel()
                        LaunchedEffectScreen(vm) {
                            navController.popBackStack()
                        }
                    }
                    SampleRouteType.MESSAGE_LIST_SCREEN -> MessageListScreen { navController.popBackStack() }
                    SampleRouteType.DISPOSABLE_EFFECT_SCREEN -> DisposableEffectScreen { navController.popBackStack() }
                    SampleRouteType.CUSTOM_VIEW_SCREEN -> CustomViewScreen { navController.popBackStack() }
                    SampleRouteType.CONSTRAINT_LAYOUT_SCREEN -> ConstraintLayoutScreen { navController.popBackStack() }
                    SampleRouteType.ROOM_SCREEN -> RoomSampleScreen { navController.popBackStack() }
                    SampleRouteType.BOTTOM_NAVIGATION_SCREEN -> BottomNavigationSampleScreen {
                        navController.popBackStack()
                    }

                    SampleRouteType.PREVIEW_PARAMETER_SCREEN -> PreviewParameterScreen(uiState = ExampleUiState(
                        memos = List(50) { Memo(title = "タイトル No.$it") }
                    )) {
                        navController.popBackStack()
                    }

                    SampleRouteType.PAGER_SCREEN -> TabInViewPagerScreen {
                        navController.popBackStack()
                    }
                    SampleRouteType.EXO_PLAYER_SCREEN -> {
                        val vm: ExoPlayerViewModel = viewModel()

                        //  VideoPlayer()
                        val context = LocalContext.current

                        //  fun createMediaItems(): MutableList<MediaItem> {
                        fun createMediaItems(): MutableList<Uri> {
//        val mediaItems = mutableListOf<MediaItem>()
                            val uriList = mutableListOf<Uri>()

                            val flowerrawDataSource = RawResourceDataSource(context)
                            val grassrawDataSource = RawResourceDataSource(context)
                            val sky_movierawDataSource = RawResourceDataSource(context)
                            flowerrawDataSource.open(
                                DataSpec(
                                    RawResourceDataSource.buildRawResourceUri(
                                        R.raw.flower
                                    )
                                )
                            )
                            grassrawDataSource.open(
                                DataSpec(
                                    RawResourceDataSource.buildRawResourceUri(
                                        R.raw.grass
                                    )
                                )
                            )
                            sky_movierawDataSource.open(
                                DataSpec(
                                    RawResourceDataSource.buildRawResourceUri(
                                        R.raw.sky_movie
                                    )
                                )
                            )
                            uriList.add(
                                flowerrawDataSource.uri!!
                            )
                            uriList.add(
                                grassrawDataSource.uri!!
                            )
                            uriList.add(
                                sky_movierawDataSource.uri!!
                            )
                            return uriList
                        }

                        val items = createMediaItems()

                        ExoPlayerSampleScreen(
                            vm,
                            { navController.popBackStack() },
                            items
                        )
                    }
                    SampleRouteType.BOTTOM_SHEET_SCREEN -> BottomSheetSampleScreen { navController.popBackStack() }
                    SampleRouteType.ROTATION_ANIMATION_SCREEN -> RotationAnimationScreen { navController.popBackStack() }
                }
            }
        }
    }
}

@Composable
fun VideoPlayer(uri: Uri) {
    val context = LocalContext.current
//    val rawDataSource = RawResourceDataSource(context)
//    rawDataSource.open(DataSpec(RawResourceDataSource.buildRawResourceUri(R.raw.flower)))

    val videoUrl =
        "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"

    var playWhenReady by rememberSaveable { mutableStateOf(true) }
    var playbackStateRemember by rememberSaveable { mutableStateOf(1) }

    val playerEventListener = object : Player.Listener {
        override fun onIsPlayingChanged(isPlaying: Boolean) {

            Log.d("TAG", "Logs = isPlaying = ${isPlaying}");

            playWhenReady = isPlaying
        }

        override fun onPlayerError(error: PlaybackException) {
//            Log.d("Logs" , "isPlayingChanged: $state")
        }

        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
            val stateStr = when (playbackState) {
                ExoPlayer.STATE_IDLE -> "STATE_IDLE"
                ExoPlayer.STATE_BUFFERING -> "STATE_BUFFERING"
                ExoPlayer.STATE_READY -> "STATE_READY"
                ExoPlayer.STATE_ENDED -> "STATE_ENDED"
                else -> "UNKNOWN"
            }

            playbackStateRemember = playbackState

            Log.d("TAG", "Logs = stateStr = $stateStr");
        }
    }

    val exoPlayer = remember(context) { // rememberでstate管理しないと、再コンポーズ時に何度も再生されてしまう。
        ExoPlayer.Builder(context).build().apply {


//            val mediaItems = createMediaItems()
            Log.d("TAG", "Logs = val exoPlayer = remember(context) { // r");

            val flowerrawDataSource = RawResourceDataSource(context)
            flowerrawDataSource.open(DataSpec(RawResourceDataSource.buildRawResourceUri(R.raw.flower)))

            val videoSource: MediaSource = ProgressiveMediaSource
                .Factory(DefaultDataSource.Factory(context))
//                .createMediaSource(MediaItem.fromUri(videoUrl))
                .createMediaSource(MediaItem.fromUri(uri)) // mp4等の場合
            this.setMediaSource(videoSource)

            this.setAudioAttributes(AudioAttributes.DEFAULT, true) // オーディオの属性を設定

            this.addListener(playerEventListener)

//            this.setMediaItems(mediaItems, true) // 動画のリストをセット、resetItem = 初期のプレイリストポジション

            this.repeatMode = Player.REPEAT_MODE_ALL // プレイリスト内をリピート再生
        }
    }

//    val playerView = PlayerControlView(context) // 非推奨→ StyledPlayerViewを使う
//    val playerView = PlayerView(context)
//    playerView.player = exoPlayer

//    val exoPlayer = ExoPlayer.Builder(context).build() // プレーヤーの作成
//    val playerView = PlayerControlView(context) // 非推奨→ StyledPlayerViewを使う
//    exoPlayer.prepare() // 準備する ※再このポーズ時にこれがないと

    LaunchedEffect(exoPlayer) {

        Log.d("TAG", "Logs =LaunchedEffect(exoPlayer) { ");

        exoPlayer.prepare() // 準備する ※再このポーズ時にこれがないと
        exoPlayer.playWhenReady = playWhenReady // trueで再生

    }

    val playerView = PlayerView(context).apply {
        player = exoPlayer // プレーヤーをビューにアタッチする
    }

//    LaunchedEffect(playWhenReady) {
//        Log.d("TAG", "Logs = LaunchedEffect(playWhenReady) {")
//    }

//    DisposableEffect( // 画面を破棄するときに、リリースする
    Column {
        Button(onClick = {

            Log.d(
                "TAG",
                "Logs =exoPlayer.nextMediaItemIndex = ${exoPlayer.nextMediaItemIndex} "
            );


        }) {
            Text(text = "テストボタン")
        }

        Button(onClick = {
            exoPlayer.next()
        }) {
            Text(text = "次のプレイリストへ")
        }

        Button(onClick = {
            exoPlayer.previous()
        }) {
            Text(text = "前のプレイリストへ")
        }

        if (!playWhenReady) {
            Button(onClick = {
                exoPlayer.play()
            }) {
                Text(text = "再生ボタン")
            }
        }
        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    runBlocking {
                        if (!playWhenReady) {
                            Log.d("TAG", "Logs = exoPlayer.play() ")
                            exoPlayer.play()
                        } else {
                            Log.d("TAG", "Logs = exoPlayer.stop()")
                            exoPlayer.pause() // 再生停止
                        }
                    }
                },
            factory = {
//                    StyledPlayerView(context).apply {
////                        controllerAutoShow = false // 再生ボタン等を表示するか
////                        useController = false // 各種ユーザーのボタンを一切表示しない
////                        useController =
//
//                        setShowRewindButton(false)
//                        setShowFastForwardButton(false)
//                        setShowPreviousButton(false)
//                        setShowNextButton(false)

////                        setShowMultiWindowTimeBar()
////                        hideController() //
//                        player = exoPlayer
//                    }
                PlayerView(context).apply {
                    player = exoPlayer // プレーヤーをビューにアタッチする
                }
//                    playerView
            })
    }
//    )
//    {
//        onDispose { // 退場時にリリースする
//            Log.d("TAG", "Logs = onDispose { exoPlayer.release() }");
//            exoPlayer.release()
//        }
//    }
}
