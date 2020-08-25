package com.alim.rushit.Adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.alim.rushit.PostViewActivity
import com.alim.rushit.ProfileActivity
import com.alim.rushit.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class HomeAdapter(context: Context, data: ArrayList<String>):
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
        val loading_pro = holder.itemView.findViewById<ProgressBar>(R.id.image_Loading_pro)
        post_image.clipToOutline = true

        loading_pro.visibility = View.VISIBLE
        Glide.with(mContext).load(mData[position])
            .centerCrop()
            .listener(object: RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean): Boolean {
                    loading_pro.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean): Boolean {
                    loading_pro.visibility = View.GONE
                    return false
                }

            })
            .into(post_image)

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