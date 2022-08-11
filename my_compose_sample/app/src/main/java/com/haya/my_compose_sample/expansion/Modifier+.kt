package com.haya.my_compose_sample.expansion

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Dp

fun Modifier.firstBaselineToTop(
    firstBaselineToTop: Dp
) = this.then(
    layout { measurable, constraints ->
        // 測定し配置する子要素
        // 子の幅と高さの最小値と最大値
        val placeable = measurable.measure(constraints)

        // ↓ 必要な意味がわからない
//        check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
        val firstBaseline = placeable[FirstBaseline] // FirstBaselineは子要素の高さを取得できる

        // Height of the composable with padding - first baseline
        val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
        val height = placeable.height + placeableY
        layout(placeable.width, height) { // layout (幅、高さ)で値を指定して返す
            // Where the composable gets placed コンポーザブルを配置する場所
            placeable.placeRelative(0, placeableY)
//            placeable.placeRelative(30, placeableY)
        }
    }
)