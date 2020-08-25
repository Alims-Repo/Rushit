package com.alim.rushit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_post.*

class PostActivity : AppCompatActivity() {

    lateinit var postButton: Button
    lateinit var postText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        close.setOnClickListener { finish() }

        postText = findViewById(R.id.post_text)
        postButton = findViewById(R.id.post)

        postText.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                postButton.isEnabled = p0.toString().isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {}

        })

        postText.setOnEditorActionListener { textView, i, keyEvent ->

            true
        }
    }
}