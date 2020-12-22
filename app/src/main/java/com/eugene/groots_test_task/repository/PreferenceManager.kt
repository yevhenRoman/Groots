package com.eugene.groots_test_task.repository

import android.content.Context
import android.content.Context.MODE_PRIVATE


class PreferenceManager(val context: Context) {
    companion object {
        const val APP_PREFERENCE = "groots_test_task"
    }

    private val preferences = context.getSharedPreferences(APP_PREFERENCE, MODE_PRIVATE)

    fun putString(key: String, value: String) {
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String, defValue: String?): String? = preferences.getString(key, defValue)

    fun putInt(key: String, value: Int) {
        val editor = preferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getInt(key: String, defValue: Int): Int? = preferences.getInt(key, defValue)

    fun clearAll() {
        preferences.edit().clear().apply()
    }
}