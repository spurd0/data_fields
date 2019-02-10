package com.babenko.datafields.application.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun loadImage(
    context: Context,
    imageView: ImageView,
    src: String,
    placeholderResId: Int?
) {
    getLoadImageRequest(context, src, placeholderResId)
        .into(imageView)
}

private fun getLoadImageRequest(
    context: Context,
    src: String,
    placeholderResId: Int?
): RequestBuilder<*> {
    return getRequestBuilder(context, src)
        .apply(getRequestOptions(placeholderResId))
}

fun loadImageWithCenterCropTransformation(
    context: Context,
    imageView: ImageView,
    src: String,
    placeholderResId: Int
) {
    var requestOptions = getRequestOptions(placeholderResId)
    requestOptions = requestOptions.centerCrop()
    getRequestBuilder(context, src)
        .apply(requestOptions)
        .into(imageView)
}

private fun getRequestBuilder(
    context: Context,
    src: String
): RequestBuilder<*> {
    return Glide.with(context)
        .load(src)
}

private fun getRequestOptions(placeholderResId: Int?): RequestOptions {
    var requestOptions = RequestOptions()
    requestOptions = requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL)
    requestOptions = requestOptions.placeholder(placeholderResId!!)
    requestOptions = requestOptions.error(placeholderResId)
    return requestOptions
}
