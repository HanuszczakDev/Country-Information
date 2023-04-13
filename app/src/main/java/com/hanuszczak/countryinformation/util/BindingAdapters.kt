package com.hanuszczak.countryinformation.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.hanuszczak.countryinformation.R

@BindingAdapter("imgUrl", "imgDescription")
fun bindFlagImage(imgView: ImageView, imgUrl: String?, imgDescription: String?) {
    val shimmer = Shimmer.AlphaHighlightBuilder()
        .setDuration(1800)
        .setBaseAlpha(0.7f)
        .setHighlightAlpha(0.6f)
        .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
        .setAutoStart(true)
        .build()

    val shimmerDrawable = ShimmerDrawable().apply {
        setShimmer(shimmer)
    }

    imgView.contentDescription = imgDescription
    imgUrl?.let {
        Glide.with(imgView.context).load(imgUrl)
            .placeholder(shimmerDrawable)
            .error(R.drawable.ic_broken_image)
            .into(imgView)
    }
}