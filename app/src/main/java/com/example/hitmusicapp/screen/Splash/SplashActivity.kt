package com.example.hitmusicapp.screen.Splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.hitmusicapp.R
import com.example.hitmusicapp.screen.authentication.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(
            {
                val intent: Intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }, 1500
        )
    }
}