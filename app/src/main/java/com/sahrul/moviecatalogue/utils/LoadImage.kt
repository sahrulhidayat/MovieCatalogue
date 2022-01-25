package com.sahrul.moviecatalogue.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sahrul.moviecatalogue.R

fun Context.loadImage(url: String?, imageView: ImageView) {
    Glide.with(this)
        .load(url)
        .apply(
            RequestOptions
                .placeholderOf(R.drawable.ic_image_loading)
                .error(R.drawable.ic_error)
        )
        .centerCrop()
        .into(imageView)
}