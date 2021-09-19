package com.example.baloot.alimehdizadeh.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageFit")
fun loadImageFit(imageView: ImageView, url: String?) {
    Glide
        .with(imageView.context)
        .load(url)
        .fitCenter()
        .into(imageView)
}


@BindingAdapter("imageCenterCrop")
fun loadImageCenterCrop(imageView: ImageView, url: String?) {
    Glide
        .with(imageView.context)
        .load(url)
        .apply(RequestOptions.centerCropTransform())
        .into(imageView)
}
