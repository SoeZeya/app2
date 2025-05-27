package com.soezeya.taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskManagerApp()
        }
    }
}

@Composable
fun TaskManagerApp() {
    val navController = rememberNavController()
    val taskViewModel: TaskViewModel = viewModel()
    val isDarkTheme = remember { mutableStateOf(false) }
    //Main screen linking with the other pages
    MaterialTheme(colors = if (isDarkTheme.value) darkColors() else lightColors()) {
        NavHost(navController = navController, startDestination = "taskScreen") {
            composable("taskScreen") { TaskScreen(navController, taskViewModel) }
            composable("settingsScreen") { SettingsScreen(navController, isDarkTheme) }
            composable("aboutScreen") { AboutScreen(navController) }
        }
    }
}