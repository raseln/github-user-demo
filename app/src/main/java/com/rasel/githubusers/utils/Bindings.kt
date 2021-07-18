package com.rasel.githubusers.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image_url")
fun showImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(imageView)
            .load(it)
            .into(imageView)
    }
}

@BindingAdapter("is_visible")
fun showVisibility(textView: TextView, value: Int?) {
    val intValue = value ?: 0
    if (intValue > 0) {
        textView.visibility = View.VISIBLE
    } else {
        textView.visibility = View.GONE
    }
}