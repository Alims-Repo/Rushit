package com.alim.rushit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.alim.rushit.Adapter.ProfileAdapter
import com.alim.rushit.Fragment.PostFragment
import com.alim.rushit.Fragment.VideoFragment
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.profile_post.*

class ProfileActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        back_icon.setOnClickListener { onBackPressed() }
        data_container.clipToOutline = true

        viewPager = findViewById(R.id.profile_view_pager)

        val adapter = ProfileAdapter(supportFragmentManager)
        adapter.addFragment(PostFragment(), "Posts")
        adapter.addFragment(VideoFragment(), "Videos")

        viewPager.adapter = adapter
        tab_profile.setupWithViewPager(viewPager)
    }
}