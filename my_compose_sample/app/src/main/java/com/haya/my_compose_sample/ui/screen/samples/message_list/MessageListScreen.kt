package com.haya.my_compose_sample.ui.screen.samples.message_list

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.haya.my_compose_sample.R
import com.haya.my_compose_sample.models.sample_models.SampleMessage
import com.haya.my_compose_sample.ui.theme.My_compose_sampleTheme


@Composable
fun MessageListScreen(toSamples: () -> Unit) {
    val messageList = mutableListOf<SampleMessage>()

    for (i in 1..100) {
        messageList.add(SampleMessage("test_author$i", "サンプルテキスト。サンプルテキスト。サンプルテキスト。サンプルテキスト。サンプルテキスト。サンプルテキスト。サンプルテキスト。サンプルテキスト。サンプルテキスト。サンプルテキスト。サンプルテキスト。$i"))
    }

    Column {
        Button(onClick = {
            toSamples()
        }) {
            Text(text = "Samples画面へ")
        }

        Text("MessageListサンプル")

        MessageList(messageList)
    }
}

@Composable
fun MessageList(messageList: List<SampleMessage>) {
    LazyColumn {
        items(messageList) { message ->
            MessageCard2(msg = message)
        }
    }
}

@Composable
fun MessageCard1(msg: SampleMessage) {
    Row(modifier = Modifier.padding(all = 8.dp).fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.ic_person),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondaryVariant, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember { mutableStateOf(false) }
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
        )

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface( // MaterialThemeが使える
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                color = surfaceColor,
                // animateContentSize will change the Surface size gradually
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}


@Composable
fun MessageCard2(msg: SampleMessage) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.ic_person),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondaryVariant, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        // 拡張されているか否か
        var isExpanded by remember { mutableStateOf(false) }
        // surfaceColor will be updated gradually from one color to the other
        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
        )

        // Modifier.clickableでクリック処理(message_body部分をクリックできる)
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = msg.author,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2 // これでもfont_wightがかかる？
            )

            Spacer(modifier = Modifier.height(4.dp))

            Surface( // Surfaceでshapeやelevationを追加できる
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                // surfaceColor color will be changing gradually from primary to surface
                color = surfaceColor,
                // animateContentSizeは、Surfaceのサイズを徐々に変化させます。
                // Surfaceで定義されている様々な要素(色、形、行数など)
                modifier = Modifier.animateContentSize().padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    // isExpandedがtrueなら、
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCard() {
    My_compose_sampleTheme {
        MessageCard1(
            msg = SampleMessage("test_author", "test_body")
        )
    }
}