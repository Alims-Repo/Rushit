package com.alim.rushit.Adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.alim.rushit.Model.ChatModel
import com.alim.rushit.PostViewActivity
import com.alim.rushit.ProfileActivity
import com.alim.rushit.R
import com.bumptech.glide.Glide

class ChatAdapter(context: Context, iUrl: String, data: ArrayList<ChatModel>):
    RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

    val url= iUrl
    val mData = data
    val mContext = context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val me = view.findViewById<FrameLayout>(R.id.me)
        val my_msg = view.findViewById<TextView>(R.id.my_msg)
        val img = view.findViewById<ImageView>(R.id.friend_img)
        val friend = view.findViewById<FrameLayout>(R.id.friend)
        val frieng_msg = view.findViewById<TextView>(R.id.friend_msg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.chat_layout,
                parent, false))
    }

    override fun onBindViewHolder(holder: ChatAdapter.ViewHolder, position: Int) {
        if (mData[position].sentby == 0) {
            holder.friend.visibility = View.GONE
            holder.me.visibility = View.VISIBLE
            holder.my_msg.text = mData[position].message
        } else {
            holder.friend.visibility = View.VISIBLE
            holder.me.visibility = View.GONE
            holder.frieng_msg.text = mData[position].message
            Glide.with(mContext).load(url).centerCrop()
                .into(holder.img)
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }
}