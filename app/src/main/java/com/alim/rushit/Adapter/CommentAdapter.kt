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

class CommentAdapter(context: Context, data: ArrayList<String>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val mData = data
    val mContext = context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.comment_layout,
                parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return mData.size
    }
}