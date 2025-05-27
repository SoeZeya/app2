package com.soezeya.taskmanager

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf

class TaskViewModel : ViewModel() {
    val tasks = mutableStateListOf<Task>()

    fun addTask(title: String) {
        tasks.add(Task(tasks.size, title))
    }

    fun toggleTaskCompletion(taskId: Int) {
        val updatedTasks = tasks.map { task ->
            if (task.id == taskId) task.copy(isCompleted = !task.isCompleted) else task
        }
        tasks.clear()
        tasks.addAll(updatedTasks)
    }
}