package co.za.immedia.superheroapp.helpers

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide

fun loadImageFromInternet(context: Context, url: String, imageView: ImageView, placeHolderPic: Int) {
    Glide.with(context).load(url).placeholder(placeHolderPic).fitCenter().into(imageView)
}

fun loadImageFromBitmap(context: Context, bitmap: Bitmap, imageView: ImageView, placeHolderPic: Int) {
    Glide.with(context).load(bitmap).placeholder(placeHolderPic).fitCenter().into(imageView)
}