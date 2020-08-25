package com.alim.rushit.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alim.rushit.R
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.fragment_settings.view.*

class SettingsFragment : Fragment() {

    val TOPIC = "/topics/myTopic2"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_settings, container, false)

        rootView.theme_switch.setOnCheckedChangeListener { compoundButton, b ->
            if (b)
                FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
            else FirebaseMessaging.getInstance().unsubscribeFromTopic(TOPIC)
        }

        return rootView
    }
}