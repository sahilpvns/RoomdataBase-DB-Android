package com.tummoc.tummoc.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.tummoc.tummoc.R

object ImageUtils {

    /**
     * @param context for provide the glide to perform the image operation
     * @param url for load the image using url
     * @param view for load the image in imageview
     */
    @JvmStatic
     fun load(context:Context,url: String?, view: ImageView) {
        Glide.with(context)
            .load(url)
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .into(view);
    }
}