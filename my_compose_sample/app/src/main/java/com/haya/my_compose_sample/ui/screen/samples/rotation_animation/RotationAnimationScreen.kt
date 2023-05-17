package com.haya.my_compose_sample.ui.screen.samples.rotation_animation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.*
import com.haya.my_compose_sample.R


@Composable
fun RotationAnimationScreen(toSamples: () -> Unit) {

    Column {
        Button(onClick = {
            toSamples()
        }) {
            Text(text = "Samples画面へ")
        }

        Text("RotationAnimationサンプル")

        RotationAnimation()
    }

}

@Composable
fun RotationAnimation() {
//    val infiniteTransition = rememberInfiniteTransition()
//
//    val angle by infiniteTransition.animateFloat(
//        initialValue = 0F,
//        targetValue = 360F,
//        animationSpec = infiniteRepeatable(tween(2000, easing = LinearEasing), RepeatMode.Restart)
//    )

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.hourglass))

    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = true, // アニメーションを開始するか否か。デフォルトでtrue
        restartOnPlay = true, // true に設定すると、再生が再開されるたびにアニメーションが初めから再生される
        clipSpec = LottieClipSpec.Frame(
            10, 50, // 10%から50%の間で再生するアニメーションをクリップ。
            true // 最大フレームを含むか否か trueだったから含む。
        ),
        speed = 1.5f, // アニメーションの速度 1.5Fにすると1.5倍になる
        iterations = 5, // アニメーションの反復回数を指定
        cancellationBehavior = LottieCancellationBehavior.Immediately, // アニメーションがキャンセルされたときの挙動を指定 ・Immediately はアニメーションが即座に停止 ・PlayOut は現在のイテレーションが終了するまでアニメーションが続く
        ignoreSystemAnimatorScale = false, // システムのアニメーションスケール設定を無視するかどうかを指定します。デフォルトは false で、システムのアニメーションスケール設定を尊重します
    )

    Column(modifier = Modifier.fillMaxSize()) {
//        Image(
//            painter = painterResource(R.drawable.ic_person),
//            contentDescription = null,
//            modifier = Modifier
//                .size(40.dp)
//                .rotate(angle)
//                .clip(CircleShape)
//                .border(1.5.dp, MaterialTheme.colors.secondaryVariant, CircleShape)
//        )

        LottieAnimation(
            composition = composition,
            progress = progress
        )

    }
}

