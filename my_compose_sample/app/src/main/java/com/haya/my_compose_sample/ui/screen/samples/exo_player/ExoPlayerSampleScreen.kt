package com.haya.my_compose_sample.ui.screen.samples.exo_player

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSpec
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import com.haya.my_compose_sample.R
import kotlinx.coroutines.runBlocking

@Composable
fun ExoPlayerSampleScreen(vm: ExoPlayerViewModel, toSamples: () -> Unit) {

//    val context = LocalContext.current
////    val url = "https://drive.google.com/file/d/194XkJ3sXTY5qv6e28aedtohDGspHQt6v/view"
////    val url = "https://d.kuku.lu/92cd5b53b"
//    val url = "https://youtu.be/2Y0GzeUL-iY"
//
//    val player = remember {
//        ExoPlayer.Builder(context).build().apply {
//            val dataSource = DefaultDataSource.Factory(context)
//            val source = ProgressiveMediaSource.Factory(dataSource)
//                .createMediaSource(MediaItem.fromUri(Uri.parse(url)))
//
//            addMediaSource(source)
//            prepare() // playerの準備
//        }
//    }
//


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red), // 背景色
        verticalArrangement = Arrangement.Center,
    ) {
        Button(onClick = {
            toSamples()
        }) {
            Text(text = "Samples画面へ")
        }
        VideoPlayer()
    }
}

@Composable
fun VideoPlayer() {
    val context = LocalContext.current
//    val rawDataSource = RawResourceDataSource(context)
//    rawDataSource.open(DataSpec(RawResourceDataSource.buildRawResourceUri(R.raw.flower)))

    val videoUrl = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"

    fun createMediaItems(): MutableList<MediaItem> {
        val mediaItems = mutableListOf<MediaItem>()

        val flowerrawDataSource = RawResourceDataSource(context)
        val grassrawDataSource = RawResourceDataSource(context)
        val sky_movierawDataSource = RawResourceDataSource(context)
        flowerrawDataSource.open(DataSpec(RawResourceDataSource.buildRawResourceUri(R.raw.flower)))
        grassrawDataSource.open(DataSpec(RawResourceDataSource.buildRawResourceUri(R.raw.grass)))
        sky_movierawDataSource.open(DataSpec(RawResourceDataSource.buildRawResourceUri(R.raw.sky_movie)))
        mediaItems.add(
            MediaItem.fromUri(flowerrawDataSource.uri!!)
        )
        mediaItems.add(
            MediaItem.fromUri(grassrawDataSource.uri!!)
        )
        mediaItems.add(
            MediaItem.fromUri(sky_movierawDataSource.uri!!)
        )
        return mediaItems
    }

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

            val mediaItems = createMediaItems()
            Log.d("TAG", "Logs = val exoPlayer = remember(context) { // r");

//            val videoSource: MediaSource = ProgressiveMediaSource
//                .Factory(DefaultDataSource.Factory(context))
//                .createMediaSource(MediaItem.fromUri(videoUrl))
//                .createMediaSource(MediaItem.fromUri(rawDataSource.uri!!)) // mp4等の場合

            this.setAudioAttributes(AudioAttributes.DEFAULT, true) // オーディオの属性を設定
//            this.setMediaSource(videoSource)
            this.addListener(playerEventListener)

            this.setMediaItems(mediaItems, true) // 動画のリストをセット、resetItem = 初期のプレイリストポジション

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

//    LaunchedEffect(playWhenReady) {
//        Log.d("TAG", "Logs = LaunchedEffect(playWhenReady) {")
//    }

    DisposableEffect( // 画面を破棄するときに、リリースする
        Column {
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
    ) {
        onDispose { // 退場時にリリースする
            Log.d("TAG", "Logs = onDispose { exoPlayer.release() }");
            exoPlayer.release()
        }
    }
}


// プレイヤーの解放 ExoPlayer.release
// プレイリストの作成とプレーヤーの準備

