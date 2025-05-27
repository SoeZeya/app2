package com.soezeya.taskmanager

import android.content.Context

class PreferencesHelper(context: Context) {
    private val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    fun getTheme(): String = prefs.getString("theme", "Light") ?: "Light"

    fun saveTheme(theme: String) {
        prefs.edit().putString("theme", theme).apply()
    }
}