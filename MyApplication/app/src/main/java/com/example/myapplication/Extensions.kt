package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

fun Context.toast(message: String, lenght: Int = Toast.LENGTH_SHORT ): Unit {
    Toast.makeText(this, message, lenght).show()
}

fun RecyclerView.ViewHolder.toast(message: String, lenght: Int = Toast.LENGTH_SHORT): Unit {
    itemView.context.toast(message, lenght)
}

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun ImageView.loadUrl(url: String){
    Picasso.with(context).load(url).into(this)
}
