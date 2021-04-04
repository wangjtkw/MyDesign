package com.example.mydesign.binding

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.mydesign.R
import com.example.mydesign.constants.BASE_URL

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        val url = "${BASE_URL}uploadimg/${imageUrl}"
        Log.d("bindImageFromUrl", "url:$url")
        Glide.with(view.context)
            .load(url)
            .error(R.drawable.ic_add)
            .into(view)

    }
}