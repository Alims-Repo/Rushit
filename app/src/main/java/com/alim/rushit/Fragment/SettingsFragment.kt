package com.alim.rushit.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alim.rushit.R
import com.facebook.shimmer.ShimmerFrameLayout


class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_settings, container, false)

        val container = rootView.findViewById(R.id.shimmer_view_container) as ShimmerFrameLayout
        container.startShimmer()

        return rootView
    }
}