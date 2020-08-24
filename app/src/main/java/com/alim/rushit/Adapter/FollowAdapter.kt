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

class FollowAdapter(context: Context, data: ArrayList<String>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val mData = data
    val mContext = context

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(mContext).inflate(R.layout.main_layout,
                parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val profile = holder.itemView.findViewById<ImageView>(R.id.profile)
        val post_image = holder.itemView.findViewById<ImageView>(R.id.post_image)
        val love_icon = holder.itemView.findViewById<ImageView>(R.id.love_icon)
        post_image.clipToOutline = true

        Glide.with(mContext).load(mData[position])
            .centerCrop().into(post_image)

        love_icon.setOnClickListener {
            love_icon.setImageDrawable(mContext.resources.getDrawable(R.drawable.ic_baseline_favorite_24))
        }

        post_image.setOnClickListener {
            val intent = Intent(mContext, PostViewActivity::class.java)
            intent.putExtra("IMAGE_LINK", mData[position])
            mContext as Activity
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                mContext,
                (post_image as View?)!!,
                "profile"
            )
            mContext.startActivity(intent, options.toBundle())
        }

        profile.setOnClickListener {
            val intent = Intent(mContext, ProfileActivity::class.java)
            mContext as Activity
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                mContext,
                (profile as View?)!!,
                "profilepic"
            )
            mContext.startActivity(intent, options.toBundle())
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }
}