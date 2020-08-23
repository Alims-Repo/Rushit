package com.alim.rushit.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alim.rushit.Adapter.ProfilePostAdapter
import com.alim.rushit.Model.ProfilePostModel
import com.alim.rushit.R
import kotlinx.android.synthetic.main.fragment_post.*

class PostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_post, container, false)

        val recycler = rootView.findViewById<RecyclerView>(R.id.post_recycler)
        //
        val data = ArrayList<ProfilePostModel>()
        val model = ProfilePostModel()
        model.description = "First description"
        model.imagelink = resources.getString(R.string.link2)
        data.add(model)
        val model1 = ProfilePostModel()
        model1.description = "Second description"
        model1.imagelink = resources.getString(R.string.link3)
        data.add(model1)
        val model2 = ProfilePostModel()
        model2.description = "Third description"
        model2.imagelink = resources.getString(R.string.link1)
        data.add(model2)
        //
        val layoutManager = LinearLayoutManager(activity!!)
        recycler.layoutManager = layoutManager
        val adapter = ProfilePostAdapter(activity!!, data)
        recycler.adapter = adapter
        adapter.notifyDataSetChanged()

        return rootView
    }
}