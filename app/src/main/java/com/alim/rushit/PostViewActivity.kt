package com.alim.rushit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alim.rushit.Adapter.CommentAdapter
import com.alim.rushit.Config.Config
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_post_view.*

class PostViewActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CommentAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_view)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        Glide.with(this).load(intent.getStringExtra("IMAGE_LINK")).into(post_image)

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

        layoutManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.comment_recycler)
        recyclerView.layoutManager = layoutManager
        adapter = CommentAdapter(this, data)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}