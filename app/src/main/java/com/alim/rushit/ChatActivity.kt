package com.alim.rushit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alim.rushit.Adapter.ChatAdapter
import com.alim.rushit.Adapter.CommentAdapter
import com.alim.rushit.Config.Config
import com.alim.rushit.Model.ChatModel
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class ChatActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ChatAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        toolbar.setNavigationOnClickListener { finish() }

        val data: ArrayList<ChatModel> = ArrayList()

        for (x in 0..200) {
            val model = ChatModel()
            model.sentby = x%2
            model.message = "Hello $x"
            data.add(model)
        }

        layoutManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.chat_recycler)
        recyclerView.layoutManager = layoutManager
        adapter = ChatAdapter(this, Config.seven, data)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

        message_send.setOnClickListener {
            if (message_edit.text.toString().isNotEmpty()) {
                val model = ChatModel()
                model.sentby = 0
                model.message = message_edit.text.toString()
                data.add(model)
                adapter.notifyItemInserted(data.size)

            }
        }
    }
}