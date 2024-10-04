package com.example.nav

import android.content.Context

class SharedPrefManager(private val context: Context) {

    private val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    // Save data (username and password)
    fun saveData(username: String, password: String) {
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.putString("password", password)
        editor.apply()
    }

    fun getData(): Pair<String?, String?> {
        val username = sharedPreferences.getString("username", null)
        val password = sharedPreferences.getString("password", null)
        return Pair(username, password)
    }

    fun clearData() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    fun changePassword(newPassword: String): Boolean {
        val username = sharedPreferences.getString("username", null)
        if (username != null) {
            // User exists, update the password
            val editor = sharedPreferences.edit()
            editor.putString("password", newPassword)
            editor.apply()
            return true
        } else {
            return false
        }
    }
}
