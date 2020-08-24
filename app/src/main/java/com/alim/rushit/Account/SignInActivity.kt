package com.alim.rushit.Account

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alim.rushit.Database.SplashScreen
import com.alim.rushit.MainActivity
import com.alim.rushit.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignInActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        container.clipToOutline = true
        mAuth = FirebaseAuth.getInstance()

        forgot.setOnClickListener {
            startActivity(Intent(this, ForgotActivity::class.java))
        }
        sign_up.setOnClickListener {
            if (intent.getStringExtra("FROM")=="SIGN_UP") finish()
            else {
                val signUpIntent = Intent(this, SignUpActivity::class.java)
                signUpIntent.putExtra("FROM", "SIGN_IN")
                startActivity(signUpIntent)
            }
        }

        sign_in.setOnClickListener {
            when {
                mail.text.toString().isEmpty() -> mail.error = "Enter your email"
                pass.text.toString().isEmpty() -> pass.error = "Enter your password"
                else -> procceed(mail.text.toString(), pass.text.toString())
            }
        }
    }

    private fun procceed(email: String, password: String) {
        login_progress.visibility = View.VISIBLE
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    SplashScreen(this@SignInActivity).splash = false
                    startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this@SignInActivity, "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
                }
                login_progress.visibility = View.GONE
            }
    }
}