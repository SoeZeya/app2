package com.soezeya.taskmanager

data class Task(
    val id: Int,
    val title: String,
    val isCompleted: Boolean = false
)