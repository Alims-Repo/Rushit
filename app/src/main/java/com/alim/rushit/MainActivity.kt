package com.alim.rushit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.alim.rushit.Adapter.MainAdapter
import com.alim.rushit.Database.SplashScreen
import com.alim.rushit.Fragment.FollowFragment
import com.alim.rushit.Fragment.HomeFragment
import com.alim.rushit.Fragment.MessagesFragment
import com.alim.rushit.Fragment.SettingsFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager
    private lateinit var fab: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.main_container)
        fab = findViewById(R.id.fab)

        val currentUser = FirebaseAuth.getInstance().currentUser
        SplashScreen(this).splash = currentUser==null

        val adapter = MainAdapter(supportFragmentManager)
        adapter.addFragment(HomeFragment(), "Home")
        adapter.addFragment(FollowFragment(), "Search")
        adapter.addFragment(MessagesFragment(), "Messages")
        adapter.addFragment(SettingsFragment(), "Settings")
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                Log.d("VIEW PAGER", "On Page Scrolled")
            }

            override fun onPageSelected(position: Int) {
                val params = toolbar.layoutParams as AppBarLayout.LayoutParams
                params.scrollFlags = (AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
                        or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS)
                bottomNavigationView.menu.getItem(position).isChecked = true
                when (position) {
                    3 -> {
                        fab.hide()
                        params.scrollFlags = 0
                        toolbar.title = "   Settings"
                        toolbarFrame.visibility = View.GONE
                    }
                    2 -> {
                        fab.hide()
                        toolbar.title = "   Messages"
                        toolbarFrame.visibility = View.GONE
                    }
                    1 -> {
                        fab.hide()
                        toolbar.title = "   Following"
                        toolbarFrame.visibility = View.GONE
                    }
                    else -> {
                        fab.show()
                        toolbarFrame.visibility = View.VISIBLE
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
                Log.d("VIEW PAGER", "On Page Scroll State Changed")
            }

        })

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> viewPager.currentItem = 0
                R.id.search -> viewPager.currentItem = 1
                R.id.chat -> viewPager.currentItem = 2
                R.id.settigs -> viewPager.currentItem = 3
            }
            false
        }

        open_profile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                (open_profile as View?)!!,
                "profilepic"
            )
            startActivity(intent, options.toBundle())
        }

        search_icon.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }

        fab.setOnClickListener {
            startActivity(Intent(this, PostActivity::class.java))
        }

        initiatNotification()
    }

    private fun initiatNotification() {
        FirebaseMessaging.getInstance().isAutoInitEnabled = true
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.e("TAG", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token

                // Log and toast
                val msg = getString(R.string.msg_token_fmt, token)
                Log.e("TAG", msg)
                //Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            })
    }
}