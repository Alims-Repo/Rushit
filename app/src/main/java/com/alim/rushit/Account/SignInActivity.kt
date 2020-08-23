package com.alim.rushit.Account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alim.rushit.R
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        container.clipToOutline = true

        forgot.setOnClickListener {
            startActivity(Intent(this, ForgotActivity::class.java))
        }
        sign_up.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}