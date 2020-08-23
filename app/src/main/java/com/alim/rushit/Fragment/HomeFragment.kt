package com.alim.rushit.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alim.rushit.Adapter.HomeAdapter
import com.alim.rushit.R

class HomeFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HomeAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        val data: ArrayList<Int> = ArrayList()
        for (x in 0..20)
            data.add(112)

        layoutManager = LinearLayoutManager(activity!!)
        recyclerView = rootView.findViewById(R.id.main_recycler)
        recyclerView.layoutManager = layoutManager
        adapter = HomeAdapter(activity!!, data)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

        return rootView
    }
}