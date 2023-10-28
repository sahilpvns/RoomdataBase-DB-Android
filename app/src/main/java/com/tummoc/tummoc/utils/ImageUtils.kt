package com.tummoc.tummoc.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.tummoc.tummoc.R

object ImageUtils {

    @JvmStatic
     fun load(context:Context,url: String?, view: ImageView) {
        Glide.with(context)
            .load(url)
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .into(view);
    }
}