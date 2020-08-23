package com.alim.rushit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.alim.rushit.Fragment.HomeFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        transaction.replace(R.id.main_container, HomeFragment(), "Home Fragment")
        transaction.addToBackStack(null)
        transaction.commit()
    }
}