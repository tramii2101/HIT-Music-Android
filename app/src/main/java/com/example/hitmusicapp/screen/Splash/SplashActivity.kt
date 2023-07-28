package com.example.hitmusicapp.screen.Splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.hitmusicapp.MainActivity
import com.example.hitmusicapp.R


class SplashActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    var nightMode: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(
            {
                val intent: Intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 1500
        )
        sharedPreferences =
            this?.getSharedPreferences("my_preferences", Context.MODE_PRIVATE) ?: sharedPreferences
        nightMode = sharedPreferences.getBoolean("nightMode", true)

        editor = sharedPreferences.edit()
        if (nightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            editor.putBoolean("nightMode", true)
        } else {

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            editor.putBoolean("nightMode", false)
        }


        editor.apply()


    }
}