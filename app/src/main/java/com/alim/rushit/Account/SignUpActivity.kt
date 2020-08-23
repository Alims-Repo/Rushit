package com.alim.rushit.Account

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alim.rushit.R
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        container.clipToOutline = true
    }
}