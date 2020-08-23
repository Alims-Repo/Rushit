package com.alim.rushit.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alim.rushit.Model.ProfilePostModel
import com.alim.rushit.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.profile_post.view.*

class ProfileVideoAdapter(context: Context, mData: ArrayList<ProfilePostModel>)
    : RecyclerView.Adapter<ProfileVideoAdapter.ViewHolder>() {

    val data = mData
    val mContext = context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val post_image = view.image_post
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): ProfileVideoAdapter.ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.profile_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileVideoAdapter.ViewHolder, position: Int) {
        holder.post_image.clipToOutline = true
        Glide.with(mContext).load(data[position].imagelink)
            .centerCrop().into(holder.post_image)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}