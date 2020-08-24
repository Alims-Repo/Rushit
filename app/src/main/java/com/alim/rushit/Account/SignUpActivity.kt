package com.alim.rushit.Account

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.alim.rushit.Database.SplashScreen
import com.alim.rushit.MainActivity
import com.alim.rushit.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        container.clipToOutline = true
        mAuth = FirebaseAuth.getInstance()

        sign_in.setOnClickListener {
            if (intent.getStringExtra("FROM")=="SIGN_IN") finish()
            else {
                val signUpIntent = Intent(this, SignInActivity::class.java)
                signUpIntent.putExtra("FROM", "SIGN_UP")
                startActivity(signUpIntent)
            }
        }

        sign_up.setOnClickListener {
            if (name.text.toString().isEmpty())
                name.error = "Enter a name"
            else if (name.text.toString().length < 3)
                name.error = "Enter a valid name"
            else if (mail.text.toString().isEmpty())
                mail.error = "Enter a email"
            else if (!mail.text.toString().contains("@"))
                mail.error = "Enter a valid email"
            else if (pass.text.toString().isEmpty())
                pass.error = "Enter a password"
            else if (pass.text.toString().length<6)
                pass.error = "Password too short"
            else if (v_pass.text.toString().isEmpty())
                v_pass.error = "Enter password again"
            else if (pass.text.toString() != v_pass.text.toString())
                v_pass.error = "Password mismatch"
            else procceed(name.text.toString(), mail.text.toString()
                , pass.text.toString())
        }
    }

    private fun procceed(name: String, mail: String, pass: String) {
        signup_progress.visibility = View.VISIBLE
        mAuth.createUserWithEmailAndPassword(mail, pass)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    SplashScreen(this@SignUpActivity).splash = false
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this@SignUpActivity, try {
                        it.exception.toString()
                    } catch (e: Exception) { "Authentication failed" },
                        Toast.LENGTH_SHORT).show();
                }
                signup_progress.visibility = View.GONE
            }
    }
}