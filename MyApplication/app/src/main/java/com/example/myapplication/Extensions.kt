package com.example.myapplication

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

fun Context.toast(message: String): Unit {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun RecyclerView.ViewHolder.toast(message: String): Unit {
    itemView.context.toast(message)
}