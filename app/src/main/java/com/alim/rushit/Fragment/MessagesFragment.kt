package com.alim.rushit.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alim.rushit.Adapter.FollowAdapter
import com.alim.rushit.Adapter.MessageAdapter
import com.alim.rushit.Config
import com.alim.rushit.R

class MessagesFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MessageAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_messages, container, false)

        val data: ArrayList<String> = ArrayList()

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

        layoutManager = LinearLayoutManager(activity!!)
        recyclerView = rootView.findViewById(R.id.message_recycler)
        recyclerView.layoutManager = layoutManager
        adapter = MessageAdapter(activity!!, data)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

        return rootView
    }
}