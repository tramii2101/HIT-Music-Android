package com.example.hitmusicapp.screen.Splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.hitmusicapp.R
import com.example.hitmusicapp.screen.authentication.login.LoginActivity
import com.example.hitmusicapp.utils.common.Constant
import java.util.*


class SplashActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private var nightMode: Boolean = true

    lateinit var language: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        sharedPreferences =
            this?.getSharedPreferences("my_preferences", Context.MODE_PRIVATE) ?: sharedPreferences
        nightMode = sharedPreferences.getBoolean("nightMode", true)
        language = sharedPreferences.getString("language", Constant.languageDefault).toString()

        editor = sharedPreferences.edit()
        Log.e("nightMode", "$nightMode")
        if (nightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            editor.putBoolean("nightMode", true)
        } else {

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            editor.putBoolean("nightMode", false)
        }


        editor.apply()

        Handler().postDelayed(
            {
                val intent: Intent = Intent(this, LoginActivity::class.java)
                editor.putBoolean("fragmentReplaced", false)
                editor.apply()
                startActivity(intent)
                finish()
            }, 1500
        )


        if (language != Constant.languageDefault) {
            val locale = Locale(language)
            Locale.setDefault(locale)

            val configuration = Configuration()
            configuration.setLocale(locale)
            resources.updateConfiguration(configuration, resources.displayMetrics)

        }


    }
}