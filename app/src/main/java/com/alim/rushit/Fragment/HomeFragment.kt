package com.alim.rushit.Fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alim.rushit.Adapter.FollowAdapter
import com.alim.rushit.Config.Config
import com.alim.rushit.PostActivity
import com.alim.rushit.R
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {

    val data: ArrayList<String> = ArrayList()
    lateinit var recyclerView: RecyclerView
    lateinit var shimmer: ShimmerFrameLayout
    private lateinit var adapter: FollowAdapter
    private lateinit var fab: FloatingActionButton
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        shimmer = rootView.findViewById(R.id.shimmer_home)
        fab = rootView.findViewById(R.id.fab)

        layoutManager = LinearLayoutManager(activity!!)
        recyclerView = rootView.findViewById(R.id.main_recycler)
        recyclerView.layoutManager = layoutManager
        adapter = FollowAdapter(activity!!, data)
        recyclerView.adapter = adapter

        if (data.size<1) {
            shimmer.visibility = View.VISIBLE
            shimmer.startShimmer()
            Handler().postDelayed({
                data.add(Config.ten)
                data.add(Config.five)
                data.add(Config.seven)
                data.add(Config.three)
                data.add(Config.one)
                data.add(Config.four)
                data.add(Config.six)
                data.add(Config.nine)
                data.add(Config.two)
                data.add(Config.eight)
                shimmer.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
                adapter.notifyDataSetChanged()
            },2000)
        } else {
            shimmer.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            adapter.notifyDataSetChanged()
        }

        fab.setOnClickListener {
            startActivity(Intent(activity!!, PostActivity::class.java))
        }

        return rootView
    }
}