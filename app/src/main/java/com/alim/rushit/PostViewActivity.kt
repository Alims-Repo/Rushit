package com.alim.rushit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_post_view.*

class PostViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_view)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        Glide.with(this).load(intent.getStringExtra(
            "IMAGE_LINK")).into(post_image)
    }
}