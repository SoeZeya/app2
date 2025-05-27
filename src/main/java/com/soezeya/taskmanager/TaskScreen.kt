package com.soezeya.taskmanager

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.foundation.text.KeyboardActions   // Import KeyboardActions to automatically delete the task after adding the task
import androidx.compose.foundation.text.KeyboardOptions
@Composable
fun TaskScreen(navController: NavController, viewModel: TaskViewModel) {
    Scaffold(
        topBar = {  //for the top bar
            TopAppBar(
                title = { Text("Task Manager") },
                actions = {
                    IconButton(onClick = { navController.navigate("settingsScreen") }) {
                        Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
                    }
                }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            var newTaskTitle by remember { mutableStateOf("") }
            val keyboardController = LocalSoftwareKeyboardController.current
                //adding and styling the textfield
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                TextField(
                    value = newTaskTitle,
                    onValueChange = { newTaskTitle = it },
                    placeholder = { Text("Enter task") },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            if (newTaskTitle.isNotEmpty()) {
                                viewModel.addTask(newTaskTitle)
                                newTaskTitle = "" // Clears text field
                                keyboardController?.hide() // Closes the keyboard
                            }
                        }
                    )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    if (newTaskTitle.isNotEmpty()) {
                        viewModel.addTask(newTaskTitle)
                        newTaskTitle = "" // Clears text field
                    }
                }) {
                    Text("Add")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column {
                viewModel.tasks.forEach { task ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Checkbox( //adjusting checkbox
                            checked = task.isCompleted,
                            onCheckedChange = { viewModel.toggleTaskCompletion(task.id) },
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = task.title,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}