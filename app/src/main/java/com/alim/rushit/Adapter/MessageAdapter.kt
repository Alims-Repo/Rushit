package com.alim.rushit.Adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.alim.rushit.PostViewActivity
import com.alim.rushit.ProfileActivity
import com.alim.rushit.R
import com.bumptech.glide.Glide

class MessageAdapter(context: Context, data: ArrayList<String>):
    RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    val mData = data
    val mContext = context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val chat_logo = view.findViewById<ImageView>(R.id.chat_logo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.chat_user_list,
                parent, false))
    }

    override fun onBindViewHolder(holder: MessageAdapter.ViewHolder, position: Int) {
        Glide.with(mContext).load(mData[position])
            .centerCrop().into(holder.chat_logo)
    }

    override fun getItemCount(): Int {
        return mData.size
    }
}