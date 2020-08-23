package com.alim.rushit.Database

import android.content.Context

class SplashScreen(val context: Context) {

    private val DATA_NAME = "SPLASH_DATA"
    private val SPLASH = "SPLASH"

    var splash: Boolean
        get() {
            val prefs = context.getSharedPreferences(DATA_NAME, 0)
            return prefs.getBoolean(SPLASH, true) }
        set(value) {
            val sharedPref = context.getSharedPreferences(DATA_NAME, 0)
            val editor = sharedPref.edit()
            editor.putBoolean(SPLASH, value)
            editor.apply() }

}