package com.example.myapplication

private val thumbBase = "http://lorempixel.com/400/400/cats/"

fun getMedia() = (1..10).map {
    MediaItem(
        "Title $it",
        "${thumbBase}$it",
        if (it % 3 == 0) MediaItem.Type.VIDEO else MediaItem.Type.PHOTO
    )
}
