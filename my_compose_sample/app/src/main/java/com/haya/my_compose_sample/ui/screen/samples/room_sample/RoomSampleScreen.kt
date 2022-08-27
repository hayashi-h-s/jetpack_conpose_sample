package com.haya.my_compose_sample.ui.screen.samples.room_sample

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.haya.my_compose_sample.App
import com.haya.my_compose_sample.models.sample_models.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat

val dao = App.database.todoDao()
val todoList = SnapshotStateList<Task>()

@Composable
fun RoomSampleScreen(toSamples: () -> Unit) {
    loadTodo()
    Column {
        Button(onClick = {
            toSamples()
        }) {
            Text(text = "Samples画面へ")
        }
        Text("Roomサンプル")
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            MainScreen(todoList)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainScreen(todoList: SnapshotStateList<Task>) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text: String by remember { mutableStateOf("") }
    val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")

    Column {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(todoList) { todo ->
                var checkedState: Boolean by remember { mutableStateOf(false) }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable(onClick = { deleteTodo(todo) })
                ) {
                    Checkbox(
                        checked = checkedState,
                        onCheckedChange = {
                            checkedState = it
                        })
                    if (checkedState) {
                        Text(
                            text = todo.task,
                            style = TextStyle(textDecoration = TextDecoration.LineThrough),
                            modifier = Modifier
                                .padding(16.dp)
                        )
                    } else {
                        Text(
                            text = todo.task,
                            modifier = Modifier
                                .padding(16.dp)
                        )
                    }
                    Text(
                        text = sdf.format(todo.time),
                        color = Color.LightGray,
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("ToDo") },
                modifier = Modifier
                    .wrapContentHeight()
                    .weight(1f)
            )
            Spacer(Modifier.size(16.dp))
            Button(
                onClick = {
                    if (text.isEmpty()) return@Button
                    postTodo(text)
                    text = ""
                    keyboardController?.hide()
                },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text("ADD")
            }
        }
    }
}

private fun loadTodo() {
    CoroutineScope(Dispatchers.Main).launch {
        withContext(Dispatchers.Default) {
            dao.getAll().forEach { todo ->
                todoList.add(todo)
            }
        }
    }
}

private fun postTodo(title: String) {
    CoroutineScope(Dispatchers.Main).launch {
        withContext(Dispatchers.Default) {
            dao.post(Task(task = title))
            todoList.clear()
            loadTodo()
        }
    }
}

private fun deleteTodo(todo: Task) {
    CoroutineScope(Dispatchers.Main).launch {
        withContext(Dispatchers.Default) {
            dao.delete(todo)
            todoList.clear()
            loadTodo()
        }
    }
}