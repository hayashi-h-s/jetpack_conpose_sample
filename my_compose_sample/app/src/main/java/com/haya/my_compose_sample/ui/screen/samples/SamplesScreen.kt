package com.haya.my_compose_sample.ui.screen.samples

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.haya.my_compose_sample.presentation.util.route.SampleRouteType

@Composable
fun SamplesScreen(
    backAction: () -> Unit,
    toSampleDetail: (String) -> Unit,
) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        Button(onClick = {
            backAction()
        }) {
            Text(text = "Topへ")
        }

        SampleRouteType.values().forEach { routeType ->
            Button(onClick = {
                toSampleDetail(routeType.route)
            }) {
                Text(text = routeType.title)
            }
        }
    }
}