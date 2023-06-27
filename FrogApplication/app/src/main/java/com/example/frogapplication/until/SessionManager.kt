package com.example.frogapplication.until

import android.content.Context
import android.content.SharedPreferences

class SessionManager (context: Context){
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("Session", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    companion object {
        private const val KEY_EMAIL = "email"
        private const val KEY_LOGGED_IN = "logged_in"
    }
    var email: String?
        get() = sharedPreferences.getString(KEY_EMAIL, null)
        set(value) {
            editor.putString(KEY_EMAIL, value)
            editor.apply()
        }
    var isLoggedIn: Boolean
        get() = sharedPreferences.getBoolean(KEY_LOGGED_IN, false)
        set(value) {
            editor.putBoolean(KEY_LOGGED_IN, value)
            editor.apply()
        }

    fun clearSession() {
        editor.clear()
        editor.apply()
    }
}