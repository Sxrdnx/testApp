package com.example.testapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

@BindingAdapter("android:imageURL")
fun setImageURL(imageView: ImageView, URL: String?) {
    imageView.alpha = 0f
    Picasso.get().load(URL).noFade().into(imageView, object : Callback {
        override fun onSuccess() {
            imageView.animate().setDuration(300).alpha(1f).start()
        }
        override fun onError(e: Exception?) {
        }
    })
}