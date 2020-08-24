package com.alim.rushit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.alim.rushit.Account.SignInActivity
import com.alim.rushit.Account.SignUpActivity
import com.alim.rushit.Database.SplashScreen
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.welcome_layout.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(if (SplashScreen(this).splash)
        R.layout.welcome_layout
        else R.layout.activity_splash)

        if (!SplashScreen(this).splash) {
            Handler().postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            },3000)
        } else {
            sign_in.setOnClickListener {
                startActivity(Intent(this, SignInActivity::class.java))
            }
            sign_up.setOnClickListener {
                startActivity(Intent(this, SignUpActivity::class.java))
            }
        }
    }
}